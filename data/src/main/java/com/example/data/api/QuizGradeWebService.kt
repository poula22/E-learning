package com.example.data.api

import com.example.data.data_classes.QuizGrade
import com.example.data.model.QuestionResponse
import com.example.data.model.QuizGradeResponse
import com.example.domain.model.QuestionResponseDTO
import com.example.domain.model.QuizGradeResponseDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface QuizGradeWebService {
    @GET("api/QuizGrades")
    suspend fun getAllQuizGrades(): List<QuestionResponse>
    @POST("api/QuizGrades")
    suspend fun quizGrades(@Body quizGrades: QuizGradeResponseDTO): Response<Void>
    @DELETE("api/QuizGrades/{id}")
    suspend fun deleteQuizGrades(@Path("id") id: Int): QuizGradeResponse
    @PUT("api/QuizGrades/{id}")
    suspend fun updateQuizGrades(@Path("id") id: Int, @Body QuizGrades: QuizGradeResponseDTO): QuizGradeResponse
    @GET("api/QuizGrades/{id}")
    suspend fun getQuizGrades(@Path("id") id: Int): QuizGradeResponse
    @GET("api/QuizGrades/GetQuizGradesByQuizId/{id}")
    suspend fun getQuizGradesByQuizId(@Path("id") id: Int): List<QuizGradeResponse>
    @GET("api/QuizGrades/GetQuizGradeByQuizIdByStudentId/{studentId}/{quizId}")
    suspend fun getQuizGradeByQuizIdByStudentId(@Path("studentId") studentId: Int
                                                , @Path("quizId") quizId: Int): QuizGradeResponse
    @GET("api/QuizGrades/QuizGradeAdder/{studentId}/{quizId}")
    suspend fun quizGradeAdder(@Path("studentId") studentId: Int
                              , @Path("quizId") quizId: Int): QuizGradeResponse
}