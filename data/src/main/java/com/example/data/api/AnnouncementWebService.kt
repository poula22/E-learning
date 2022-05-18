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
    suspend fun addAnnouncement(@Query("announcement")announcement: Announcement): AnnouncementResponse
    @POST("h1")
    suspend fun updateAnnouncement(@Query("announcement") apiKey:String): AnnouncementResponse
    @DELETE("h1")
    suspend fun deleteAnnouncement(@Query("id") id:Int): AnnouncementResponse
    @GET("h1")
    suspend fun getAllAnnouncement(): List<AnnouncementResponse>
    @GET("h1")
    suspend fun getAnnouncementById(@Query("id") id:Int): AnnouncementResponse
}