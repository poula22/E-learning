package com.example.data.api

import com.example.data.data_classes.Lesson
import com.example.data.model.LessonResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LessonWebService {
    @POST("h1")
    fun addLesson(@Query("lesson")lesson: Lesson): Call<LessonResponse>
    @POST("h1")
    fun updateLesson(@Query("lesson") apiKey:String): Call<LessonResponse>
    @DELETE("h1")
    fun deleteLesson(@Query("id") id:Int): Call<LessonResponse>
    @GET("h1")
    fun getAllLesson(): Call<List<LessonResponse>>
    @GET("h1")
    fun getLessonById(@Query("id") id:Int): Call<LessonResponse>
}