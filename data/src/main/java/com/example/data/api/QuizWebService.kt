package com.example.data.api

import com.example.data.data_classes.Quiz
import com.example.data.model.QuizResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface QuizWebService {
    @POST("h1")
    fun addQuiz(@Query("quiz")quiz: Quiz): Call<QuizResponse>
    @POST("h1")
    fun updateQuiz(@Query("quiz") apiKey:String): Call<QuizResponse>
    @DELETE("h1")
    fun deleteQuiz(@Query("id") id:Int): Call<QuizResponse>
    @GET("h1")
    fun getAllQuiz(): Call<List<QuizResponse>>
    @GET("h1")
    fun getQuizById(@Query("id") id:Int): Call<QuizResponse>
}