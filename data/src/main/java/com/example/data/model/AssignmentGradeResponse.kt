package com.example.data.model

import com.google.gson.annotations.SerializedName


data class AssignmentGradeResponse(

    @field:SerializedName("assignmentAnswerID")
    val assignmentAnswerID: Int? = null,

    @field:SerializedName("assignmentAnswer")
    val assignmentAnswer: AssignmentAnswer? = null,

    @field:SerializedName("grade")
    val grade: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null
)


