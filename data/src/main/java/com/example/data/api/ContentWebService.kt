package com.example.data.api

import com.example.data.data_classes.Content
import com.example.data.model.ContentResponse
import com.example.domain.model.ContentResponseDTO
import retrofit2.Call
import retrofit2.http.*

interface ContentWebService {
    @GET("api/Contents")
    suspend fun getAllContents(): List<ContentResponse>
    @POST("api/Contents")
    suspend fun addContent(@Body content: ContentResponseDTO): ContentResponse
    @PUT("api/Contents/{id}")
    suspend fun updateContent(@Path("id") id:Int,
                              content:ContentResponseDTO): ContentResponse
    @DELETE("api/Contents/{id}")
    suspend fun deleteContent(@Path("id") id:Int): ContentResponse
    @GET("api/Contents/{id}")
    suspend fun getContentById(@Path("id") id:Int): ContentResponse
    @GET("api/Contents/GetContentsByLessonId/{lessonId}")
    suspend fun getContentsByLessonId(@Path("lessonId") lessonId:Int): List<ContentResponse>
}