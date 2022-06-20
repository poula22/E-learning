package com.example.data.api

import com.example.data.data_classes.Feature
import com.example.data.model.FeatureResponse
import com.example.domain.model.FeatureResponseDTO
import retrofit2.Call
import retrofit2.http.*

interface FeatureWebService {
    @GET("api/Features")
    suspend fun getAllFeatures(): List<FeatureResponse>
    @POST("api/Features")
    suspend fun addFeature(@Body feature: FeatureResponseDTO): FeatureResponse
    @DELETE("api/Features/{id}")
    suspend fun deleteFeature(@Path("id") id: Int): FeatureResponse
    @GET("api/Features/{id}")
    suspend fun getFeature(@Path("id") id: Int): FeatureResponse
    @PUT("api/Features/{id}")
    suspend fun updateFeature(@Path("id") id: Int, @Body feature: FeatureResponseDTO): FeatureResponse


}