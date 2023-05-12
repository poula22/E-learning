package com.example.data.api

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface TeacherWebService {
    @Multipart
    @POST("api/Teachers")
    suspend fun addTeacher(
        @Part firstName: MultipartBody.Part?,
        @Part lastName: MultipartBody.Part?,
        @Part phone: MultipartBody.Part?,
        @Part role: MultipartBody.Part?,
        @Part email: MultipartBody.Part?,
        @Part password: MultipartBody.Part?,
        @Part profilePic: MultipartBody.Part? = null
    ): Response<Void>
}