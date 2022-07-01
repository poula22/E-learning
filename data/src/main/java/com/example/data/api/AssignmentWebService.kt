package com.example.data.api

import com.example.data.model.AssignmentDetailsResponse
import com.example.data.model.AssignmentResponse
import com.example.domain.model.AssignmentResponseDTO
import okhttp3.MultipartBody
import retrofit2.http.*

interface AssignmentWebService {
    @POST("api/Assignments")
    suspend fun addAssignment(@Body assignment: AssignmentResponseDTO): AssignmentResponse

    @PUT("api/Assignments/{id}")
    suspend fun updateAssignment(
        @Path("id") id: Int,
        @Body assignment: AssignmentResponseDTO
    ): AssignmentResponse

    @DELETE("api/Assignments/{id}")
    suspend fun deleteAssignment(@Path("id") id: Int): AssignmentResponse

    @GET("api/Assignments")
    suspend fun getAllAssignment(): List<AssignmentResponse>

    @GET("api/Assignments/{id}")
    suspend fun getAssignmentById(@Path("id") id: Int): AssignmentResponse

    @GET("api/Assignments/GetAssignmentsByCourseId/{courseId}")
    suspend fun getAssignmentsByCourseId(@Path("courseId") courseId: Int): List<AssignmentResponse>

    @GET("api/Assignments/GetAssignmentsByCourseIdForStudent/{courseId}/{studentId}")
    suspend fun getAssignmentsByCourseIdForStudent(
        @Path("courseId") courseId: Int,
        @Path("studentId") studentId: Int
    ): List<AssignmentDetailsResponse>

    @PUT("api/Assignments/update-file/{id}")
    suspend fun updateAssignmentFileByAssignmentId(
        @Path("id") assignmentId: Int,
        @Part file: MultipartBody.Part
    ): AssignmentResponse


}