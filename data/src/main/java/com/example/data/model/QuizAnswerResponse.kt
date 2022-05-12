package com.example.data.model

import com.google.gson.annotations.SerializedName


data class Quiz(
    val any: Any? = null
)

data class QuizGrade(
    val any: Any? = null
)

data class QuizAnswerResponse(

    @field:SerializedName("quiz")
    val quiz: Quiz? = null,

    @field:SerializedName("studentID")
    val studentID: Int? = null,

    @field:SerializedName("submitTime")
    val submitTime: String? = null,

    @field:SerializedName("answer")
    val answer: String? = null,

    @field:SerializedName("quizID")
    val quizID: Int? = null,

    @field:SerializedName("student")
    val student: Student? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("quizGrade")
    val quizGrade: QuizGrade? = null
)

