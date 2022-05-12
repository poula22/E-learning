package com.example.data.api

import com.example.data.data_classes.Student
import com.example.data.model.StudentResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StudentWebService {
    @POST("h1")
    fun addStudent(@Query("student")student: Student): Call<StudentResponse>
    @POST("h1")
    fun updateStudent(@Query("student") apiKey:String): Call<StudentResponse>
    @DELETE("h1")
    fun deleteStudent(@Query("id") id:Int): Call<StudentResponse>
    @GET("h1")
    fun getAllStudent(): Call<List<StudentResponse>>
    @GET("h1")
    fun getStudentById(@Query("id") id:Int): Call<StudentResponse>
}