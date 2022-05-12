package com.example.data.api

import com.example.data.data_classes.Feature
import com.example.data.model.FeatureResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FeatureWebService {
    @POST("h1")
    fun addFeature(@Query("feature")feature: Feature): Call<FeatureResponse>
    @POST("h1")
    fun updateFeature(@Query("feature") apiKey:String): Call<FeatureResponse>
    @DELETE("h1")
    fun deleteFeature(@Query("id") id:Int): Call<FeatureResponse>
    @GET("h1")
    fun getAllFeature(): Call<List<FeatureResponse>>
    @GET("h1")
    fun getFeatureById(@Query("id") id:Int): Call<FeatureResponse>
}