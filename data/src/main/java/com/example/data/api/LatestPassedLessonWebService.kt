package com.example.data.api

import com.example.data.data_classes.LatestPassedLesson
import com.example.data.model.LatestPassedLessonResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LatestPassedLessonWebService {
    @POST("h1")
    fun addLatestPassedLesson(@Query("latestPassedLesson")latestPassedLesson: LatestPassedLesson): Call<LatestPassedLessonResponse>
    @POST("h1")
    fun updateLatestPassedLesson(@Query("latestPassedLesson") apiKey:String): Call<LatestPassedLessonResponse>
    @DELETE("h1")
    fun deleteLatestPassedLesson(@Query("id") id:Int): Call<LatestPassedLessonResponse>
    @GET("h1")
    fun getAllLatestPassedLesson(): Call<List<LatestPassedLessonResponse>>
    @GET("h1")
    fun getLatestPassedLessonById(@Query("id") id:Int): Call<LatestPassedLessonResponse>
}