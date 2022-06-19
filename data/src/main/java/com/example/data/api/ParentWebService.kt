package com.example.data.api

import com.example.data.data_classes.Parent
import com.example.data.model.ParentResponse
import retrofit2.Call
import retrofit2.http.*

interface ParentWebService {
    @POST("api/Parents")
    suspend fun addParent(@Body parent: Parent): ParentResponse
    @GET("api/Parents/AddStudentsByEmailToParent/{parentId}/{studentEmail}")
    suspend fun addStudentsByEmailToParent(@Path("parentId") parentId:Int
                                           , @Path("studentEmail") studentEmail:String): List<ParentResponse>
}