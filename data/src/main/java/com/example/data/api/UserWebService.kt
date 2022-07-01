package com.example.data.api

import com.example.data.model.UserResponse
import com.example.domain.model.UserResponseDTO
import retrofit2.http.*

interface UserWebService {
    @GET("api/Users")
    suspend fun getAllUsers(): List<UserResponse>

    @GET("api/Users/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserResponse

    @PUT("api/Users/{id}")
    suspend fun updateUserById(
        @Path("id") id: Int, @Body user: UserResponseDTO
    ): UserResponse

    @DELETE("api/Users/{id}")
    suspend fun deleteUserById(@Path("id") id: Int): UserResponse

    @POST("api/Users/login/{email}/{password}")
    suspend fun logIn(
        @Path("email") email: String, @Path("password") password: String
    ): UserResponse

    @POST("api/Users/LoginTest")
    suspend fun logInTest(@Body user: UserResponseDTO): UserResponse
}