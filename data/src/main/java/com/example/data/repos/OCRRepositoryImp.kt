package com.example.data.repos

import com.example.domain.model.OCRResponseDTO
import com.example.domain.repos.OCRRepository
import com.example.domain.repos.data_sources.OCROnlineDataSource
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadOperationResult

class OCRRepositoryImp(val ocrOnlineDataSource: OCROnlineDataSource) : OCRRepository {
    override suspend fun getTextFromImage(language: String, url: String): OCRResponseDTO {
        var result = ocrOnlineDataSource.getTextFromImage(language = language, url = url)
        return result
    }

    override suspend fun getTextFromImageReadApi(
        language: String?,
        url: String
    ): ReadOperationResult {
        var result = ocrOnlineDataSource.getTextFromImageReadApi(language = language, url = url)
        return result
    }

    override suspend fun getTextFromImageReadApi(
        language: String?,
        image: ByteArray
    ): ReadOperationResult {
        var result = ocrOnlineDataSource.getTextFromImageReadApi(language, image)
        return result
    }
}