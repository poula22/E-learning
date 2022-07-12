package com.example.data.api

import com.example.data.model.RecitationParagraphResponse
import com.example.domain.model.RecitationParagraphRequestDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface RecitationWebService {

    @POST("api/TextSimilarities")
    suspend fun getSimilarity(
        @Body request: RecitationParagraphRequestDTO
    ): RecitationParagraphResponse


}