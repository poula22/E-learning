package com.example.data.api

import com.example.data.model.AssignmentAnswerDetailsResponse
import com.example.data.model.AssignmentAnswerResponse
import com.example.domain.model.AssignmentAnswerResponseDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface AssignmentAnswerWebService {
    @POST("api/AssignmentAnswers")
    suspend fun addAssignmentAnswer(@Body body: RequestBody): AssignmentAnswerResponse

    @PUT("api/AssignmentAnswers/{id}")
    suspend fun updateAssignmentAnswer(
        @Path("id") id: Int, @Body assignmentAnswer: AssignmentAnswerResponseDTO
    ): AssignmentAnswerResponse

    @DELETE("api/AssignmentAnswers/{id}")
    suspend fun deleteAssignmentAnswer(@Path("id") id: Int): AssignmentAnswerResponse

    @GET("api/AssignmentAnswers")
    suspend fun getAllAssignmentAnswer(): List<AssignmentAnswerResponse>

    @GET("api/AssignmentAnswers/{id}")
    suspend fun getAssignmentAnswerById(@Path("id") id: Int): AssignmentAnswerResponse

    @GET("api/AssignmentAnswers/GetAssignmentAnswersByAssignmentId/{assignmentId}")
    suspend fun getAssignmentAnswersByAssignmentId(@Path("assignmentId") assignmentId: Int): List<AssignmentAnswerDetailsResponse>

    @GET("api/AssignmentAnswers/GetAssignmentAnswerByStudentIdByAssignmentId/{studentId}/{assignmentId}")
    suspend fun getAssignmentAnswerByStudentIdByAssignmentId(
        @Path("studentId") studentID: Int, @Path("assignmentId") assignmentId: Int
    ): AssignmentAnswerResponse

    @PUT("api/AssignmentAnswers/update-file/{id}")
    fun updateAssignmentAnswerFileByAssignmentAnswerId(
        @Path("id") id: Int,
        @Body body: RequestBody
    ):AssignmentAnswerResponse

    @PUT("api/AssignmentAnswers/Add/Update/AssignmentAnswers/MultipleAssignedGrades")
    suspend fun updateMultipleAssignedGrades(
        @Body grades: List<AssignmentAnswerResponseDTO>
    ): AssignmentAnswerResponse

    @PUT("api/AssignmentAnswers/Add/Update/AssignmentAnswers/AssignedGrade")
    suspend fun updateAssignmentGrade(
        @Body grade: AssignmentAnswerResponseDTO
    ): AssignmentAnswerResponse

}