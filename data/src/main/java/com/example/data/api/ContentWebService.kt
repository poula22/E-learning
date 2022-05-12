package com.example.data.api

import com.example.data.data_classes.Content
import com.example.data.model.ContentResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ContentWebService {
    @POST("h1")
    fun addContent(@Query("content")content: Content): Call<ContentResponse>
    @POST("h1")
    fun updateContent(@Query("content") apiKey:String): Call<ContentResponse>
    @DELETE("h1")
    fun deleteContent(@Query("id") id:Int): Call<ContentResponse>
    @GET("h1")
    fun getAllContent(): Call<List<ContentResponse>>
    @GET("h1")
    fun getContentById(@Query("id") id:Int): Call<ContentResponse>
}