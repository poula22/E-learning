package com.example.data.api

import com.example.data.data_classes.ToDoList
import com.example.data.model.ToDoListResponse
import com.example.domain.model.TodoDTO
import retrofit2.Call
import retrofit2.http.*

interface ToDoListWebService {
    @GET("api/ToDoLists")
    suspend fun getToDoLists(): ToDoListResponse
    @POST("api/ToDoLists")
    suspend fun addToDoList(@Body toDoList:TodoDTO): ToDoListResponse
    @PUT("api/ToDoLists/{id}")
    suspend fun updateToDoList(@Path("id") id:Int, @Body toDoList:TodoDTO): ToDoListResponse
    @DELETE("api/ToDoLists/{id}")
    suspend fun deleteToDoList(@Path("id") id:Int): ToDoListResponse
    @GET("api/ToDoLists/{id}")
    suspend fun getToDoList(@Path("id") id:Int): ToDoListResponse
    @GET("api/ToDoLists/GetToDoListsByUserId/{userId}")
    suspend fun getToDoListsByUserId(@Path("userId") userId:Int): ToDoListResponse
}