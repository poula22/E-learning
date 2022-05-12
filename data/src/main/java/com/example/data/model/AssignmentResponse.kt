package com.example.data.model

import com.google.gson.annotations.SerializedName


data class AssignmentResponse(

    @field:SerializedName("assignmentAnswers")
    val assignmentAnswers: List<Any?>? = null,

    @field:SerializedName("deadlineTime")
    val deadlineTime: String? = null,

    @field:SerializedName("deadlineDate")
    val deadlineDate: String? = null,

    @field:SerializedName("filePath")
    val filePath: String? = null,

    @field:SerializedName("grade")
    val grade: Int? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("course")
    val course: Course? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("assignmentID")
    val assignmentID: Int? = null,

    @field:SerializedName("showDate")
    val showDate: String? = null,

    @field:SerializedName("courseID")
    val courseID: Int? = null
)
