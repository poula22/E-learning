package com.example.data.api

import com.example.data.model.ParentStudentResponse
import com.example.domain.model.ParentStudentRequestDTO
import retrofit2.http.*

interface ParentStudentWebService {

    @POST("api/ParentStudent")
    suspend fun verifyStudentRequest(@Body student: ParentStudentRequestDTO) : ParentStudentResponse

    @GET("api/ParentStudent/GetUnVerifiedParentStudentRequests/{studentId}")
    suspend fun getUnVerifiedParentStudentRequests(@Path("studentId") studentId: Int): List<ParentStudentResponse>

    @PUT("api/ParentStudent/VerifyParentStudentRequest/{parentId}/{studentId}")
    suspend fun verifyParentStudentRequest(
        @Path("parentId") parentId: Int,
        @Path("studentId") studentId: Int
    ) : ParentStudentResponse

    @DELETE("api/ParentStudent/DropParentStudentRequest/{parentId}/{studentId}")
    suspend fun dropParentStudentRequest(
        @Path("parentId") parentId: Int,
        @Path("studentId") studentId: Int
    ) : ParentStudentResponse

}