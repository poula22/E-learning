package com.example.data.api

import com.example.data.model.SummarizationResponse
import com.example.domain.model.SummarizationTextRequestDTO
import com.example.domain.model.SummarizationUrlRequestDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface SummarizationWebService {

    @POST("api/TextSummarization/Text")
    suspend fun getSummarizationForText(
        @Body summarizationResponse: SummarizationTextRequestDTO
    ): SummarizationResponse

    @POST("api/TextSummarization/URL")
    suspend fun getSummarizationForUrl(
        @Body summarizationResponse: SummarizationUrlRequestDTO
    ): SummarizationResponse

}