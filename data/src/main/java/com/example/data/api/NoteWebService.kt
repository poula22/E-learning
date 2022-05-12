package com.example.data.api

import com.example.data.data_classes.Note
import com.example.data.model.NoteResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NoteWebService {
    @POST("h1")
    fun addNote(@Query("note")note: Note): Call<NoteResponse>
    @POST("h1")
    fun updateNote(@Query("note") apiKey:String): Call<NoteResponse>
    @DELETE("h1")
    fun deleteNote(@Query("id") id:Int): Call<NoteResponse>
    @GET("h1")
    fun getAllNote(): Call<List<NoteResponse>>
    @GET("h1")
    fun getNoteById(@Query("id") id:Int): Call<NoteResponse>
}