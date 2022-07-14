package com.example.data.api

import com.example.data.model.QuizResponse
import com.example.data.model.TeacherQuizResponse
import com.example.domain.model.QuizResponseDTO
import com.example.domain.model.TeacherQuizResponseDTO
import retrofit2.Response
import retrofit2.http.*

interface QuizWebService {
    @GET("api/Quizes")
    suspend fun getAllQuizzes(): List<TeacherQuizResponse>

    @POST("api/Quizes")
    suspend fun createQuiz(@Body quiz: TeacherQuizResponseDTO): Response<Void>

    @GET("api/Quizes/{id}")
    suspend fun getQuizById(@Path("id") id: Int): TeacherQuizResponse

    @PUT("api/Quizes/{id}")
    suspend fun updateQuiz(@Path("id") id: Int, @Body quiz: TeacherQuizResponseDTO): TeacherQuizResponse

    @DELETE("api/Quizes/{id}")
    suspend fun deleteQuiz(@Path("id") id: Int)

    @GET("api/Quizes/GetQuizzesByCourseId/{courseId}")
    suspend fun getQuizzesByCourseId(@Path("courseId") courseId: Int): List<TeacherQuizResponse>


}