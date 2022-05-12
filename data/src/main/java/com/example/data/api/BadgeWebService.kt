package com.example.data.api

import com.example.data.data_classes.Badge
import com.example.data.model.BadgeResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BadgeWebService {
    @POST("h1")
    fun addBadge(@Query("badge")badge: Badge): Call<BadgeResponse>
    @POST("h1")
    fun updateBadge(@Query("badge") apiKey:String): Call<BadgeResponse>
    @DELETE("h1")
    fun deleteBadge(@Query("id") id:Int): Call<BadgeResponse>
    @GET("h1")
    fun getAllBadge(): Call<List<BadgeResponse>>
    @GET("h1")
    fun getBadgeById(@Query("id") id:Int): Call<BadgeResponse>
}