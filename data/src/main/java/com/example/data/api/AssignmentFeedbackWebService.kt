package com.example.data.api

import com.example.data.data_classes.AssignmentFeedback
import com.example.data.model.AssignmentFeedbackResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AssignmentFeedbackWebService {
    @POST("h1")
    fun addAssignmentFeedback(@Query("assignmentFeedback")assignmentFeedback: AssignmentFeedback): Call<AssignmentFeedbackResponse>
    @POST("h1")
    fun updateAssignmentFeedback(@Query("assignmentFeedback") apiKey:String): Call<AssignmentFeedbackResponse>
    @DELETE("h1")
    fun deleteAssignmentFeedback(@Query("id") id:Int): Call<AssignmentFeedbackResponse>
    @GET("h1")
    fun getAllAssignmentFeedback(): Call<List<AssignmentFeedbackResponse>>
    @GET("h1")
    fun getAssignmentFeedbackById(@Query("id") id:Int): Call<AssignmentFeedbackResponse>
}