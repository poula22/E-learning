package com.example.data.api

import com.example.data.data_classes.Assignment
import com.example.data.model.AssignmentResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AssignmentWebService {
    @POST("h1")
    fun addAssignment(@Query("assignment")assignment: Assignment): Call<AssignmentResponse>
    @POST("h1")
    fun updateAssignment(@Query("assignment") apiKey:String): Call<AssignmentResponse>
    @DELETE("h1")
    fun deleteAssignment(@Query("id") id:Int): Call<AssignmentResponse>
    @GET("h1")
    fun getAllAssignment(): Call<List<AssignmentResponse>>
    @GET("h1")
    fun getAssignmentById(@Query("id") id:Int): Call<AssignmentResponse>
}