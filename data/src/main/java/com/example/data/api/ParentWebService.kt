package com.example.data.api

import com.example.data.data_classes.Parent
import com.example.data.model.ParentResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ParentWebService {
    @POST("h1")
    fun addParent(@Query("parent")parent: Parent): Call<ParentResponse>
    @POST("h1")
    fun updateParent(@Query("parent") apiKey:String): Call<ParentResponse>
    @DELETE("h1")
    fun deleteParent(@Query("id") id:Int): Call<ParentResponse>
    @GET("h1")
    fun getAllParent(): Call<List<ParentResponse>>
    @GET("h1")
    fun getParentById(@Query("id") id:Int): Call<ParentResponse>
}