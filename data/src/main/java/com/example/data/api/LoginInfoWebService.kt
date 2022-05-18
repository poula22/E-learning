package com.example.data.api

import com.example.data.data_classes.LoginInfo
import com.example.data.model.LoginInfoResponse
import retrofit2.Call
import retrofit2.http.*

interface LoginInfoWebService {
    @POST("h1")
    suspend fun addLoginInfo(@Query("loginInfo")loginInfo: LoginInfo): LoginInfoResponse
    @POST("h1")
    suspend fun updateLoginInfo(@Query("loginInfo") apiKey:String): LoginInfoResponse
    @DELETE("h1")
    suspend fun deleteLoginInfo(@Query("id") id:Int): LoginInfoResponse
    @GET("h1")
    suspend fun getAllLoginInfo(): List<LoginInfoResponse>
    @GET("h1")
    suspend fun getLoginInfoById(@Query("id") id:Int): LoginInfoResponse

    @Headers("accept: text/plain")
    @GET("api/LoginInfo")
    suspend fun test():List<LoginInfoResponse>
}