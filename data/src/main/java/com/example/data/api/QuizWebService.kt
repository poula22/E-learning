package com.example.data.api

import com.example.data.data_classes.Quiz
import com.example.data.model.QuizResponse
import com.example.domain.model.QuizResponseDTO
import retrofit2.Call
import retrofit2.http.*

interface QuizWebService {
    @GET("api/Quizes")
    suspend fun getAllQuizzes(): List<QuizResponse>
    @POST("api/Quizes")
    suspend fun createQuiz(@Body quiz: QuizResponseDTO): QuizResponse
    @GET("api/Quizes/{id}")
    suspend fun getQuizById(@Path("id") id: Int): QuizResponse
    @PUT("api/Quizes/{id}")
    suspend fun updateQuiz(@Path("id") id: Int, @Body quiz: QuizResponseDTO): QuizResponse
    @DELETE("api/Quizes/{id}")
    suspend fun deleteQuiz(@Path("id") id: Int)
    @GET("api/Quizes/GetQuizzesByCourseId/{courseId}")
    suspend fun getQuizzesByCourseId(@Path("courseId") courseId: Int): List<QuizResponse>
}