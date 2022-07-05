package com.example.data.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.File

interface FileTransferService {
    @GET("Images/d5f8abc8-c567-43dc-a911-58327d69448a.jpeg")
    suspend fun test(): ResponseBody

    @GET("Images/{name}")
    suspend fun getFile(@Path("name") fileName:String): ResponseBody


}