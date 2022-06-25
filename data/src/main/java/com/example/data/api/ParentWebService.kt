package com.example.data.api

import com.example.data.data_classes.Parent
import com.example.data.model.ParentResponse
import com.example.data.model.UserResponse
import com.example.domain.model.ParentResponseDTO
import retrofit2.Call
import retrofit2.http.*

interface ParentWebService {
    @POST("api/Parents")
    suspend fun addParent(@Body parent: ParentResponseDTO): UserResponse

    @GET("api/Parents/AddStudentsByEmailToParent/{parentId}/{studentEmail}")
    suspend fun addStudentsByEmailToParent(@Path("parentId") parentId:Int
                                           , @Path("studentEmail") studentEmail:String): List<ParentResponse>
}