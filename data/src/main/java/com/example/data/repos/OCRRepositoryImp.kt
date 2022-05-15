package com.example.data.repos

import com.example.data.api.ApiManager
import com.example.domain.repos.OCROnlineDataSource
import com.example.domain.repos.OCRRepository
import com.example.domain.model.OCRResponseDTO
import com.example.domain.model.ReadOCRResponseDTO
import kotlinx.coroutines.CoroutineScope

class OCRRepositoryImp(val ocrOnlineDataSource: OCROnlineDataSource):OCRRepository {
    override suspend fun getTextFromImage(language: String, url: String): OCRResponseDTO {
        var result=ocrOnlineDataSource.getTextFromImage(language=language,url=url)
        return result
    }
    override suspend fun getTextFromImageReadApi(language: String?, url: String): ReadOCRResponseDTO {
        var result=ocrOnlineDataSource.getTextFromImageReadApi(language=language,url=url)
        return result
    }
}