package com.example.data.api

import com.example.data.model.ParentResponse
import com.example.data.model.UserResponse
import com.example.domain.model.ParentResponseDTO
import okhttp3.MultipartBody
import retrofit2.http.*

interface ParentWebService {
    @Multipart
    @POST("api/Parents")
    suspend fun addParent(@Part firstName: MultipartBody.Part?,
                          @Part lastName: MultipartBody.Part?,
                          @Part phone: MultipartBody.Part?,
                          @Part role: MultipartBody.Part?,
                          @Part email: MultipartBody.Part?,
                          @Part password: MultipartBody.Part?,
                          @Part profilePic: MultipartBody.Part? = null): UserResponse

    @GET("api/Parents/{parentId}/AddStudentsByEmailToParent/{studentEmail}")
    suspend fun addStudentsByEmailToParent(
        @Path("parentId") parentId: Int, @Path("studentEmail") studentEmail: String
    ): List<ParentResponse>
}