package com.example.data.api

import com.example.data.model.AssignmentDetailsResponse
import com.example.data.model.AssignmentResponse
import com.example.data.model.NewAssignmentResponse
import com.example.domain.model.AssignmentResponseDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface AssignmentWebService {
    @POST("api/Assignments")
    suspend fun addAssignment(@Body body: RequestBody): Response<Void>

    @PUT("api/Assignments/{id}")
    suspend fun updateAssignment(
        @Path("id") id: Int,
        @Body assignment: AssignmentResponseDTO
    ): AssignmentResponse

    @DELETE("api/Assignments/{id}")
    suspend fun deleteAssignment(@Path("id") id: Int): Response<Void>

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

    @Multipart
    @PUT("api/Assignments/update-file/{id}")
    fun updateAssignmentFileByAssignmentId(
        @Path("id") assignmentId: Int,
        @Part file: MultipartBody.Part
    ): Call<AssignmentResponse>

    @GET("api/Assignments/GetAssignmentGrades/ByCourseId/ByStudentId/ForTeacher/{courseId}/{studentId}")
    suspend fun getAssignmentGradesByCourseIdByStudentIdForTeacher(
        @Path("courseId") courseId: Int,
        @Path("studentId") studentId: Int
    ): List<NewAssignmentResponse>


}