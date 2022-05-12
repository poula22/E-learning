package com.example.data.api

import com.example.data.data_classes.Question
import com.example.data.model.QuestionResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface QuestionWebService {
    @POST("h1")
    fun addQuestion(@Query("question")question: Question): Call<QuestionResponse>
    @POST("h1")
    fun updateQuestion(@Query("question") apiKey:String): Call<QuestionResponse>
    @DELETE("h1")
    fun deleteQuestion(@Query("id") id:Int): Call<QuestionResponse>
    @GET("h1")
    fun getAllQuestion(): Call<List<QuestionResponse>>
    @GET("h1")
    fun getQuestionById(@Query("id") id:Int): Call<QuestionResponse>
}