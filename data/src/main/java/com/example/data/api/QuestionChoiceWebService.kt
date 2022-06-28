package com.example.data.api

import com.example.data.model.QuestionChoiceResponse
import com.example.domain.model.QuestionChoiceResponseDTO
import retrofit2.http.*

interface QuestionChoiceWebService {

    @GET("api/QuestionChoices")
    suspend fun getAllQuestionChoices(): List<QuestionChoiceResponse>

    @POST("api/QuestionChoices")
    suspend fun addQuestionChoices(@Body questionChoice: QuestionChoiceResponseDTO): QuestionChoiceResponse

    @PUT("api/QuestionChoices/{id}")
    suspend fun updateQuestionChoices(
        @Path("id") id: Int,
        @Body questionChoice: QuestionChoiceResponseDTO
    ): QuestionChoiceResponse

    @DELETE("api/QuestionChoices/{id}")
    suspend fun deleteQuestionChoices(@Path("id") id: Int): QuestionChoiceResponse

    @GET("api/QuestionChoices/{id}")
    suspend fun getQuestionChoices(@Path("id") id: Int): QuestionChoiceResponse

    @GET("api/QuestionChoices/GetQuestionChoicesByQuestionId/{questionId}")
    suspend fun getQuestionChoicesByQuestionId(@Path("questionId") questionId: Int): List<QuestionChoiceResponse>

    @POST("api/QuestionAnswers/PostMultipleQuestionChoices")
    suspend fun postMultipleQuestionChoices(@Body questionChoice: QuestionChoiceResponse): QuestionChoiceResponse

}