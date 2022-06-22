package com.example.data.api

import com.example.data.data_classes.Assignment
import com.example.data.model.AssignmentDetailsResponse
import com.example.data.model.AssignmentResponse
import com.example.domain.model.AssignmentResponseDTO
import retrofit2.Call
import retrofit2.http.*

interface AssignmentWebService {
    @POST("api/Assignments")
    suspend fun addAssignment(@Body assignment: AssignmentResponseDTO): AssignmentResponse
    @PUT("api/Assignments/{id}")
    suspend fun updateAssignment(@Path("id") id:Int): AssignmentResponse
    @DELETE("api/Assignments/{id}")
    suspend fun deleteAssignment(@Path("id") id:Int): AssignmentResponse
    @GET("api/Assignments")
    suspend fun getAllAssignment(): List<AssignmentResponse>
    @GET("api/Assignments/{id}")
    suspend fun getAssignmentById(@Path("id") id:Int): AssignmentResponse
    @GET("api/Assignments/GetAssignmentsByCourseId/{courseId}")
    suspend fun getAssignmentsByCourseId(@Path("courseId") courseId:Int): List<AssignmentResponse>
    @GET("api/Assignments/GetAssignmentsByCourseIdForStudent/{courseId}/{studentId}")
    suspend fun getAssignmentsByCourseIdForStudent(@Path("courseId") courseId:Int, @Path("studentId") studentId:Int): List<AssignmentDetailsResponse>
}