package com.example.data.api

import com.example.data.data_classes.Announcement
import com.example.data.model.AnnouncementResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AnnouncementWebService {
    @POST("h1")
    fun addAnnouncement(@Query("announcement")announcement: Announcement): Call<AnnouncementResponse>
    @POST("h1")
    fun updateAnnouncement(@Query("announcement") apiKey:String): Call<AnnouncementResponse>
    @DELETE("h1")
    fun deleteAnnouncement(@Query("id") id:Int): Call<AnnouncementResponse>
    @GET("h1")
    fun getAllAnnouncement(): Call<List<AnnouncementResponse>>
    @GET("h1")
    fun getAnnouncementById(@Query("id") id:Int): Call<AnnouncementResponse>
}