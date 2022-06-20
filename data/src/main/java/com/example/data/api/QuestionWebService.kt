package com.example.data.api

import com.example.data.data_classes.Question
import com.example.data.model.QuestionResponse
import com.example.domain.model.QuestionResponseDTO
import retrofit2.Call
import retrofit2.http.*

interface QuestionWebService {
    @GET("api/questions")
    suspend fun getQuestions(): List<QuestionResponse>
    @POST("api/questions")
    suspend fun addQuestion(@Body question: QuestionResponseDTO): QuestionResponse
    @DELETE("api/Questions/{id}")
    suspend fun deleteQuestion(@Path("id") id: Int): QuestionResponse
    @PUT("api/Questions/{id}")
    suspend fun updateQuestion(@Path("id") id: Int, @Body question: QuestionResponseDTO): QuestionResponse
    @GET("api/Questions/{id}")
    suspend fun getQuestion(@Path("id") id: Int): QuestionResponse
    @GET("api/Questions/GetQuestionsByQuizId/{id}")
    suspend fun getQuestionsByQuizId(@Path("id") id: Int): List<QuestionResponse>
}