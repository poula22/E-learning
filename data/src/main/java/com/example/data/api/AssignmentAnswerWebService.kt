package com.example.data.api

import com.example.data.data_classes.AssignmentAnswer
import com.example.data.model.AssignmentAnswerResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AssignmentAnswerWebService {
    @POST("h1")
    suspend fun addAssignmentAnswer(@Query("assignmentAnswer")assignmentAnswer: AssignmentAnswer): AssignmentAnswerResponse
    @POST("h1")
    suspend fun updateAssignmentAnswer(@Query("assignmentAnswer") apiKey:String): AssignmentAnswerResponse
    @DELETE("h1")
    suspend fun deleteAssignmentAnswer(@Query("id") id:Int): AssignmentAnswerResponse
    @GET("h1")
    fun getAllAssignmentAnswer(): Call<List<AssignmentAnswerResponse>>
    @GET("h1")
    fun getAssignmentAnswerById(@Query("id") id:Int): Call<AssignmentAnswerResponse>
}