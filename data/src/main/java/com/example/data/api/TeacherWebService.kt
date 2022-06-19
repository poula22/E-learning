package com.example.data.api

import com.example.data.data_classes.Teacher
import com.example.data.model.TeacherResponse
import com.example.domain.model.TeacherResponseDTO
import retrofit2.Call
import retrofit2.http.*

interface TeacherWebService {
        @POST("api/Teachers")
        suspend fun addTeacher(@Body teacher: TeacherResponseDTO):TeacherResponse
}