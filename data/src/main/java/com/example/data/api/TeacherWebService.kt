package com.example.data.api

import com.example.data.data_classes.Teacher
import com.example.data.model.TeacherResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TeacherWebService {
    @POST("h1")
    fun addTeacher(@Query("teacher")teacher: Teacher): Call<TeacherResponse>
    @POST("h1")
    fun updateTeacher(@Query("teacher") apiKey:String): Call<TeacherResponse>
    @DELETE("h1")
    fun deleteTeacher(@Query("id") id:Int): Call<TeacherResponse>
    @GET("h1")
    fun getAllTeacher(): Call<List<TeacherResponse>>
    @GET("h1")
    fun getTeacherById(@Query("id") id:Int): Call<TeacherResponse>
}