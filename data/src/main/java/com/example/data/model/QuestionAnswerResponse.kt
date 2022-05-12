package com.example.data.model

import com.google.gson.annotations.SerializedName


data class QuestionAnswerResponse(

    @field:SerializedName("studentID")
    val studentID: Int? = null,

    @field:SerializedName("questionID")
    val questionID: Int? = null,

    @field:SerializedName("question")
    val question: Question? = null,

    @field:SerializedName("student")
    val student: Student? = null,

    @field:SerializedName("questionAnswerID")
    val questionAnswerID: Int? = null,

    @field:SerializedName("state")
    val state: Boolean? = null,

    @field:SerializedName("questionAnswerText")
    val questionAnswerText: String? = null
)

data class Question(
    val any: Any? = null
)


