package com.example.data.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.File

interface FileTransferService {
    @GET("Images/{fileName}")
    suspend fun getImage(@Path("fileName") fileName: String): ResponseBody

    @GET("Files/{fileName}")
    suspend fun getFile(@Path("fileName") fileName:String): ResponseBody

    @GET("Videos/{fileName}")
    suspend fun getVideo(@Path("fileName") fileName:String): ResponseBody

    @GET("Images/{fileName}")
    fun getImageCall(@Path("fileName") fileName: String): Call<ResponseBody>

}