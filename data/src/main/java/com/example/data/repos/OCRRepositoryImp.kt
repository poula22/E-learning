package com.example.data.repos

import com.example.domain.repos.OCROnlineDataSource
import com.example.domain.repos.OCRRepository
import com.example.domain.repos.OCRResponseDTO

class OCRRepositoryImp(val ocrOnlineDataSource: OCROnlineDataSource):OCRRepository {
    override suspend fun getTextFromImage(language: String, url: String): OCRResponseDTO {
        var result=ocrOnlineDataSource.getTextFromImage(language=language,url=url)
        return result
    }
}