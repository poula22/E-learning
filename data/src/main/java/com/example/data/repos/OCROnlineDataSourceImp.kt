package com.example.data.repos

import com.example.data.api.ApiManager
import com.example.data.api.microsoft_api.ocr.MicrosoftOCRApiManager
import com.example.data.api.microsoft_api.ocr.MicrosoftOCRWebService
import com.example.data.data_classes.URLOCR
import com.example.data.model.convertTo
import com.example.domain.model.OCRResponseDTO
import com.example.domain.repos.data_sources.OCROnlineDataSource
import com.microsoft.azure.cognitiveservices.vision.computervision.implementation.ComputerVisionImpl
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadHeaders
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadInStreamHeaders
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadOperationResult


class OCROnlineDataSourceImp(private val webService: MicrosoftOCRWebService = ApiManager.getOCRApi()) :
    OCROnlineDataSource {
    override suspend fun getTextFromImage(language: String, url: String): OCRResponseDTO {
        var u = URLOCR(url = url)
        var result = webService.getTextFromImage(language = language, url = u)
        return result.convertTo(OCRResponseDTO::class.java)
    }

    override suspend fun getTextFromImageReadApi(
        language: String?,
        url: String
    ): ReadOperationResult {
        println("-----------------------------------------------")
        println("Read with URL: $url")
        try {
            // Cast Computer Vision to its implementation to expose the required methods
            val vision: ComputerVisionImpl =
                MicrosoftOCRApiManager.client.computerVision() as ComputerVisionImpl

            // Read in remote image and response header
            val responseHeader: ReadHeaders =
                vision.readWithServiceResponseAsync(url, null)
                    .toBlocking()
                    .single()
                    .headers()

            // Extract the operation Id from the operationLocation header
            val operationLocation: String = responseHeader.operationLocation()
            println("Operation Location:$operationLocation")
            var result = MicrosoftOCRApiManager.getAndPrintReadResult(vision, operationLocation)
            return result
        } catch (e: Exception) {
            println(e.message)
            e.printStackTrace()
            return ReadOperationResult()
        }
    }

    override suspend fun getTextFromImageReadApi(
        language: String?,
        image: ByteArray
    ): ReadOperationResult {

        try {
            // Cast Computer Vision to its implementation to expose the required methods
            val vision: ComputerVisionImpl =
                MicrosoftOCRApiManager.client.computerVision() as ComputerVisionImpl

            // Read in remote image and response header
            val responseHeader: ReadInStreamHeaders =
                vision.readInStreamWithServiceResponseAsync(image, null)
                    .toBlocking()
                    .single()
                    .headers()

            // Extract the operation Id from the operationLocation header
            val operationLocation: String = responseHeader.operationLocation()
            println("Operation Location:$operationLocation")
            var result = MicrosoftOCRApiManager.getAndPrintReadResult(vision, operationLocation)
            return result
        } catch (e: Exception) {
            println(e.message)
            e.printStackTrace()
            return ReadOperationResult()
        }
    }

    private suspend fun getHeader(language: String? = null, url: String): String {
        var u = URLOCR(url = url)
        var result = webService.getTextFromImageReadAPI(url = u).headers().get("Operation-Location")
        var index = result?.indexOf("analyzeResults/")
        return result?.substring(index?.plus(15)!!)!!
    }

}