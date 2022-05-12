package com.example.data.api

import com.example.data.data_classes.AssignmentGrade
import com.example.data.model.AssignmentGradeResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AssignmentGradeWebService {
    @POST("h1")
    fun addAssignmentGrade(@Query("assignmentGrade")assignmentGrade: AssignmentGrade): Call<AssignmentGradeResponse>
    @POST("h1")
    fun updateAssignmentGrade(@Query("assignmentGrade") apiKey:String): Call<AssignmentGradeResponse>
    @DELETE("h1")
    fun deleteAssignmentGrade(@Query("id") id:Int): Call<AssignmentGradeResponse>
    @GET("h1")
    fun getAllAssignmentGrade(): Call<List<AssignmentGradeResponse>>
    @GET("h1")
    fun getAssignmentGradeById(@Query("id") id:Int): Call<AssignmentGradeResponse>
}