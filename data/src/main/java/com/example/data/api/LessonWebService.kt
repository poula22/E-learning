package com.example.data.api

import com.example.data.model.LessonResponse
import com.example.domain.model.LessonResponseDTO
import retrofit2.Response
import retrofit2.http.*

interface LessonWebService {
    @GET("api/Lessons")
    suspend fun getAllLessons(): List<LessonResponse>

    @POST("api/Lessons")
    suspend fun addLesson(@Body lesson: LessonResponseDTO): LessonResponse

    @GET("api/Lessons/{id}")
    suspend fun getLesson(@Path("id") id: Int): LessonResponse

    @PUT("api/Lessons/{id}")
    suspend fun updateLesson(@Path("id") id: Int, @Body lesson: LessonResponseDTO): LessonResponse

    @DELETE("api/Lessons/{id}")
    suspend fun deleteLesson(@Path("id") id: Int): Response<Void>

    @GET("api/Lessons/GetLessonsByCourseId/{courseId}")
    suspend fun getLessonsByCourseId(@Path("courseId") courseId: Int): List<LessonResponse>

}