package com.example.data.api

import com.example.data.data_classes.Resource
import com.example.data.model.ResourceResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ResourceWebService {
    @POST("h1")
    fun addResource(@Query("resource")resource: Resource): Call<ResourceResponse>
    @POST("h1")
    fun updateResource(@Query("resource") apiKey:String): Call<ResourceResponse>
    @DELETE("h1")
    fun deleteResource(@Query("id") id:Int): Call<ResourceResponse>
    @GET("h1")
    fun getAllResource(): Call<List<ResourceResponse>>
    @GET("h1")
    fun getResourceById(@Query("id") id:Int): Call<ResourceResponse>
}