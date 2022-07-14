package com.example.data.api

import com.example.data.model.QuestionAnswerResponse
import com.example.domain.model.QuestionAnswerResponseDTO
import retrofit2.Response
import retrofit2.http.*

interface QuestionAnswerWebService {
    @GET("api/QuestionAnswers")
    suspend fun getAllQuestionAnswers(): List<QuestionAnswerResponse>

    @POST("api/QuestionAnswers")
    suspend fun addQuestionAnswer(@Body questionAnswer: QuestionAnswerResponseDTO): QuestionAnswerResponse

    @PUT("api/QuestionAnswers/{id}")
    suspend fun updateQuestionAnswer(
        @Path("id") id: Int,
        @Body questionAnswer: QuestionAnswerResponseDTO
    ): QuestionAnswerResponse

    @DELETE("api/QuestionAnswers/{id}")
    suspend fun deleteQuestionAnswer(@Path("id") id: Int): QuestionAnswerResponse

    @GET("api/QuestionAnswers/{id}")
    suspend fun getQuestionAnswer(@Path("id") id: Int): QuestionAnswerResponse

    @GET("api/QuestionAnswers/CorrectQuestionAnswerOrNot/{questionId}/{questionAnswerId}")
    suspend fun getCorrectQuestionAnswerOrNot(
        @Path("questionId") questionId: Int, @Path("questionAnswerId") questionAnswerId: Int
    ): QuestionAnswerResponse

    @GET("api/QuestionAnswers/GetQuestionAnswersByQuestionId/{questionId}")
    suspend fun getQuestionAnswersByQuestionId(@Path("questionId") questionId: Int): List<QuestionAnswerResponse>

    @GET("api/QuestionAnswers/GetQuestionAnswerByQuestionIdByStudentId/{studentId}/{questionId}")
    suspend fun getQuestionAnswerByQuestionIdByStudentId(
        @Path("studentId") studentId: Int, @Path("questionId") questionId: Int
    ): QuestionAnswerResponse


    @POST("api/QuestionAnswers/PostMultipleQuestionAnswers")
    suspend fun postMultipleQuestionAnswers(@Body questionAnswers: List<QuestionAnswerResponseDTO>): Response<Void>


}