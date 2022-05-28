package com.example.data.api.microsoft_api.ocr

import com.example.data.api.ApiManager
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVision
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionClient
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionManager
import com.microsoft.azure.cognitiveservices.vision.computervision.implementation.ComputerVisionImpl
import com.microsoft.azure.cognitiveservices.vision.computervision.models.OperationStatusCodes
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadHeaders
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadOperationResult
import java.util.*

class MicrosoftOCRApiManager {
    companion object {
        var subscriptionKey = "ad4b918f3bbb4349828bdf501dc8592c"
        var endpoint = "https://ocrtestpolapoula.cognitiveservices.azure.com/"
        val client: ComputerVisionClient = authenticate(subscriptionKey, endpoint)


        private fun authenticate(
            subscriptionKey: String?,
            endpoint: String?
        ): ComputerVisionClient {
            return ComputerVisionManager.authenticate(subscriptionKey).withEndpoint(endpoint)
        }
        fun readFromUrl(url:String) {
            println("-----------------------------------------------")
            println("Read with URL: $url")
            try {
                // Cast Computer Vision to its implementation to expose the required methods
//                val vision: ComputerVisionImpl = client.computerVision() as ComputerVisionImpl
                val vision: ComputerVisionImpl = ApiManager.getRetrofitVisionApi()
                // Read in remote image and response header
                val responseHeader: ReadHeaders =
                    vision.readWithServiceResponseAsync(url, null)
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
        @Throws(InterruptedException::class)
         fun getAndPrintReadResult(vision: ComputerVision, operationLocation: String):ReadOperationResult{
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
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
            return readResults
        }
        private fun extractOperationIdFromOpLocation(operationLocation: String?): String {
            if (operationLocation != null && operationLocation.isNotEmpty()) {
                val splits = operationLocation.split("/").toTypedArray()
                if (splits.isNotEmpty()) {
                    return splits[splits.size - 1]
                }
            }
            throw IllegalStateException("Something went wrong: Couldn't extract the operation id from the operation location")
        }

    }
}