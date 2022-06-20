package com.example.data.api

import com.example.data.data_classes.Lesson
import com.example.data.model.LessonResponse
import com.example.domain.model.LessonResponseDTO
import retrofit2.Call
import retrofit2.http.*

interface LessonWebService {
    @GET("lesson")
    suspend fun getAllLessons(): List<LessonResponse>
    @POST("api/Lessons")
    suspend fun addLesson(@Body lesson: LessonResponseDTO): LessonResponse
    @PUT("api/Lessons/{id}")
    suspend fun updateLesson(@Path("id") id: Int, @Body lesson: LessonResponseDTO): LessonResponse
    @DELETE("api/Lessons/{id}")
    suspend fun deleteLesson(@Path("id") id: Int): LessonResponse
    @GET("api/Lessons/GetLessonsByCourseId/{courseId}")
    suspend fun getLessonsByCourseId(@Path("courseId") courseId: Int): List<LessonResponse>

}