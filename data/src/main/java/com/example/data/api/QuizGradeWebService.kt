package com.example.data.api

import com.example.data.data_classes.QuizGrade
import com.example.data.model.QuizGradeResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface QuizGradeWebService {
    @POST("h1")
    fun addQuizGrade(@Query("quizGrade")quizGrade: QuizGrade): Call<QuizGradeResponse>
    @POST("h1")
    fun updateQuizGrade(@Query("quizGrade") apiKey:String): Call<QuizGradeResponse>
    @DELETE("h1")
    fun deleteQuizGrade(@Query("id") id:Int): Call<QuizGradeResponse>
    @GET("h1")
    fun getAllQuizGrade(): Call<List<QuizGradeResponse>>
    @GET("h1")
    fun getQuizGradeById(@Query("id") id:Int): Call<QuizGradeResponse>
}