package com.example.lamp.ui.sign_in_page

import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVision
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionClient
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionManager
import com.microsoft.azure.cognitiveservices.vision.computervision.implementation.ComputerVisionImpl
import com.microsoft.azure.cognitiveservices.vision.computervision.models.OperationStatusCodes
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadHeaders
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadOperationResult
import java.util.*

class MicrosoftOCRTest {
    companion object  {
        var subscriptionKey = "c81d3df2-b7d2-4112-aa7b-d2395196bf58"
        var endpoint = "ad4b918f3bbb4349828bdf501dc8592c"

        @JvmStatic
        fun microsoftOcrTest() {
            println("\nAzure Cognitive Services Computer Vision - Java Quickstart Sample")

            // Create an authenticated Computer Vision client.
            val compVisClient: ComputerVisionClient = authenticate(subscriptionKey, endpoint)

            // Read from remote image
            readFromUrl(compVisClient)
        }

        fun authenticate(subscriptionKey: String?, endpoint: String?): ComputerVisionClient {
            return ComputerVisionManager.authenticate(subscriptionKey).withEndpoint(endpoint)
        }

        /**
         * OCR with READ : Performs a Read Operation
         * @param client instantiated vision client
         */
        private fun readFromUrl(client: ComputerVisionClient) {
            println("-----------------------------------------------")
            val remoteTextImageURL =
                "https://raw.githubusercontent.com/Azure-Samples/cognitive-services-sample-data-files/master/ComputerVision/Images/printed_text.jpg"
            println("Read with URL: $remoteTextImageURL")
            try {
                // Cast Computer Vision to its implementation to expose the required methods
                val vision: ComputerVisionImpl = client.computerVision() as ComputerVisionImpl

                // Read in remote image and response header
                val responseHeader: ReadHeaders =
                    vision.readWithServiceResponseAsync(remoteTextImageURL, null)
                        .toBlocking()
                        .single()
                        .headers()

                // Extract the operation Id from the operationLocation header
                val operationLocation: String = responseHeader.operationLocation()
                println("Operation Location:$operationLocation")
                getAndPrintReadResult(vision, operationLocation)
            } catch (e: Exception) {
                println(e.message)
                e.printStackTrace()
            }
        }

        /**
         * Extracts the OperationId from a Operation-Location returned by the POST Read operation
         * @param operationLocation
         * @return operationId
         */
        private fun extractOperationIdFromOpLocation(operationLocation: String?): String {
            if (operationLocation != null && operationLocation.isNotEmpty()) {
                val splits = operationLocation.split("/").toTypedArray()
                if (splits.isNotEmpty()) {
                    return splits[splits.size - 1]
                }
            }
            throw IllegalStateException("Something went wrong: Couldn't extract the operation id from the operation location")
        }

        /**
         * Polls for Read result and prints results to console
         * @param vision Computer Vision instance
         * @return operationLocation returned in the POST Read response header
         */
        @Throws(InterruptedException::class)
        private fun getAndPrintReadResult(vision: ComputerVision, operationLocation: String) {
            println("Polling for Read results ...")

            // Extract OperationId from Operation Location
            val operationId = extractOperationIdFromOpLocation(operationLocation)
            var pollForResult = true
            var readResults: ReadOperationResult? = null
            while (pollForResult) {
                // Poll for result every second
                Thread.sleep(1000)
                readResults = vision.getReadResult(UUID.fromString(operationId))

                // The results will no longer be null when the service has finished processing the request.
                if (readResults != null) {
                    // Get request status
                    val status: OperationStatusCodes = readResults.status()
                    if (status === OperationStatusCodes.FAILED || status === OperationStatusCodes.SUCCEEDED) {
                        pollForResult = false
                    }
                }
            }

            // Print read results, page per page
            for (pageResult in readResults!!.analyzeResult().readResults()) {
                println("")
                println("Printing Read results for page " + pageResult.page())
                val builder = StringBuilder()
                for (line in pageResult.lines()) {
                    builder.append(line.text())
                    builder.append("\n")
                }
                println(builder.toString())
            }
        }
    }}
