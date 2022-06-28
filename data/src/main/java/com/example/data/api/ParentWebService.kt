package com.example.data.api

import com.example.data.model.ParentResponse
import com.example.data.model.UserResponse
import com.example.domain.model.ParentResponseDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ParentWebService {
    @POST("api/Parents")
    suspend fun addParent(@Body parent: ParentResponseDTO): UserResponse

    @GET("api/Parents/{parentId}/AddStudentsByEmailToParent/{studentEmail}")
    suspend fun addStudentsByEmailToParent(
        @Path("parentId") parentId: Int, @Path("studentEmail") studentEmail: String
    ): List<ParentResponse>
}