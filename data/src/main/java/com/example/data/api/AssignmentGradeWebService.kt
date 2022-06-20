package com.example.data.api

import com.example.data.data_classes.AssignmentGrade
import com.example.data.model.AssignmentGradeResponse
import com.example.domain.model.AssignmentGradeResponseDTO
import retrofit2.Call
import retrofit2.http.*

interface AssignmentGradeWebService {
    @POST("api/AssignmentGrades")
    suspend fun addAssignmentGrade(@Body assignmentGrade: AssignmentGradeResponseDTO): AssignmentGradeResponse
    @PUT("api/AssignmentGrades/{id}")
    suspend fun updateAssignmentGrade(@Path("id") id:Int
                              ,@Body assignmentGrade: AssignmentGradeResponseDTO): AssignmentGradeResponse
    @DELETE("api/AssignmentGrades/{id}")
    suspend fun deleteAssignmentGrade(@Path("id") id:Int): AssignmentGradeResponse
    @GET("api/AssignmentGrades")
    suspend fun getAllAssignmentGrade(): List<AssignmentGradeResponse>
    @GET("api/AssignmentGrades/GetAssignmentGradeByAssignmentAnswerId/{assignmentAnswerId}")
    suspend fun getAssignmentGradeByAnswerId(@Path("assignmentAnswerId") assignmentAnswerId:Int): AssignmentGradeResponse
}