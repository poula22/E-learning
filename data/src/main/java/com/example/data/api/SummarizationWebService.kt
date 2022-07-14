package com.example.data.api

import com.example.data.model.SummarizationResponse
import com.example.domain.model.SummarizationTextRequestDTO
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface SummarizationWebService {

    @POST("api/TextSummarization/{type}")
    suspend fun getSummarizationForText(
        @Path("type") type: String,
        @Body summarizationResponse: SummarizationTextRequestDTO
    ): SummarizationResponse

}