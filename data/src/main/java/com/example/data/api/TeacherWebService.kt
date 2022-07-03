package com.example.data.api

import com.example.data.data_classes.Teacher
import com.example.data.model.TeacherResponse
import com.example.data.model.UserResponse
import com.example.domain.model.TeacherResponseDTO
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.net.IDN

interface TeacherWebService {
        @Multipart
        @POST("api/Teachers")
        suspend fun addTeacher(@Part firstName: MultipartBody.Part?
                               ,@Part lastName: MultipartBody.Part?
                               ,@Part phone: MultipartBody.Part?
                               ,@Part profilePic: MultipartBody.Part?=null
                               ,@Part role: MultipartBody.Part?
                               ,@Part email:MultipartBody.Part?
                               ,@Part password:MultipartBody.Part?):Response<Void>
}