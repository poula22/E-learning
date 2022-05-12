package com.example.data.model

import com.google.gson.annotations.SerializedName


data class QuizResponse(

    @field:SerializedName("instructions")
    val instructions: String? = null,

    @field:SerializedName("quizFile")
    val quizFile: String? = null,

    @field:SerializedName("grade")
    val grade: Int? = null,

    @field:SerializedName("course")
    val course: Course? = null,

    @field:SerializedName("startTime")
    val startTime: String? = null,

    @field:SerializedName("quizAnswers")
    val quizAnswers: List<Any?>? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("endTime")
    val endTime: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("modelAnswer")
    val modelAnswer: String? = null,

    @field:SerializedName("courseID")
    val courseID: Int? = null,

    @field:SerializedName("startDate")
    val startDate: String? = null
)
