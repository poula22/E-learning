package com.example.data.api

import com.example.data.data_classes.QuizAnswer
import com.example.data.model.QuizAnswerResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface QuizAnswerWebService {
    @POST("h1")
    fun addQuizAnswer(@Query("quizAnswer")quizAnswer: QuizAnswer): Call<QuizAnswerResponse>
    @POST("h1")
    fun updateQuizAnswer(@Query("quizAnswer") apiKey:String): Call<QuizAnswerResponse>
    @DELETE("h1")
    fun deleteQuizAnswer(@Query("id") id:Int): Call<QuizAnswerResponse>
    @GET("h1")
    fun getAllQuizAnswer(): Call<List<QuizAnswerResponse>>
    @GET("h1")
    fun getQuizAnswerById(@Query("id") id:Int): Call<QuizAnswerResponse>
}