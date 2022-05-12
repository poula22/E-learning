package com.example.data.api

import com.example.data.data_classes.ToDoList
import com.example.data.model.ToDoListResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ToDoListWebService {
    @POST("h1")
    fun addToDoList(@Query("toDoList")toDoList: ToDoList): Call<ToDoListResponse>
    @POST("h1")
    fun updateToDoList(@Query("toDoList") apiKey:String): Call<ToDoListResponse>
    @DELETE("h1")
    fun deleteToDoList(@Query("id") id:Int): Call<ToDoListResponse>
    @GET("h1")
    fun getAllToDoList(): Call<List<ToDoListResponse>>
    @GET("h1")
    fun getToDoListById(@Query("id") id:Int): Call<ToDoListResponse>
}