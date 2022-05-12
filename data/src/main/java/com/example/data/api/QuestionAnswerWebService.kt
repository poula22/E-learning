package com.example.data.api

import com.example.data.data_classes.QuestionAnswer
import com.example.data.model.QuestionAnswerResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface QuestionAnswerWebService {
    @POST("h1")
    fun addQuestionAnswer(@Query("questionAnswer")questionAnswer: QuestionAnswer): Call<QuestionAnswerResponse>
    @POST("h1")
    fun updateQuestionAnswer(@Query("questionAnswer") apiKey:String): Call<QuestionAnswerResponse>
    @DELETE("h1")
    fun deleteQuestionAnswer(@Query("id") id:Int): Call<QuestionAnswerResponse>
    @GET("h1")
    fun getAllQuestionAnswer(): Call<List<QuestionAnswerResponse>>
    @GET("h1")
    fun getQuestionAnswerById(@Query("id") id:Int): Call<QuestionAnswerResponse>
}