package com.example.data.api

import com.example.data.data_classes.LoginInfo
import com.example.data.model.LoginInfoResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginInfoWebService {
    @POST("h1")
    fun addLoginInfo(@Query("loginInfo")loginInfo: LoginInfo): Call<LoginInfoResponse>
    @POST("h1")
    fun updateLoginInfo(@Query("loginInfo") apiKey:String): Call<LoginInfoResponse>
    @DELETE("h1")
    fun deleteLoginInfo(@Query("id") id:Int): Call<LoginInfoResponse>
    @GET("h1")
    fun getAllLoginInfo(): Call<List<LoginInfoResponse>>
    @GET("h1")
    fun getLoginInfoById(@Query("id") id:Int): Call<LoginInfoResponse>
}