package com.example.data.api

import com.example.data.model.AssignmentAnswerResponse
import com.example.domain.model.AssignmentAnswerResponseDTO
import retrofit2.http.*

interface AssignmentAnswerWebService {
    @POST("api/AssignmentAnswers")
    suspend fun addAssignmentAnswer(@Body assignmentAnswer: AssignmentAnswerResponseDTO): AssignmentAnswerResponse

    @PUT("api/AssignmentAnswers/{id}")
    suspend fun updateAssignmentAnswer(
        @Path("id") id: Int, @Body assignmentAnswer: AssignmentAnswerResponseDTO
    ): AssignmentAnswerResponse

    @DELETE("api/AssignmentAnswers/{id}")
    suspend fun deleteAssignmentAnswer(@Path("id") id: Int): AssignmentAnswerResponse

    @GET("api/AssignmentAnswers")
    fun getAllAssignmentAnswer(): List<AssignmentAnswerResponse>

    @GET("api/AssignmentAnswers/{id}")
    fun getAssignmentAnswerById(@Path("id") id: Int): AssignmentAnswerResponse

    @GET("api/AssignmentAnswers/GetAssignmentAnswersByAssignmentId/{assignmentId}")
    fun getAssignmentAnswersByAssignmentId(@Path("assignmentId") assignmentId: Int): List<AssignmentAnswerResponse>

    @GET("api/AssignmentAnswers/GetAssignmentAnswerByStudentIdByAssignmentId/{studentId}/{assignmentId}")
    fun getAssignmentAnswerByStudentIdByAssignmentId(
        @Path("studentId") studentID: Int, @Path("assignmentId") assignmentId: Int
    ): AssignmentAnswerResponse

}