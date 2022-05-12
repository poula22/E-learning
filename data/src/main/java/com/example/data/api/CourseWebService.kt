package com.example.data.api

import com.example.data.model.Course
import com.example.data.model.CourseResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CourseWebService {
    @POST("h1")
    fun addCourse(@Query("course")course: Course): Call<CourseResponse>
    @POST("h1")
    fun updateCourse(@Query("course") apiKey:String): Call<CourseResponse>
    @DELETE("h1")
    fun deleteCourse(@Query("id") id:Int): Call<CourseResponse>
    @GET("h1")
    fun getAllCourse(): Call<List<CourseResponse>>
    @GET("h1")
    fun getCourseById(@Query("id") id:Int): Call<CourseResponse>
}