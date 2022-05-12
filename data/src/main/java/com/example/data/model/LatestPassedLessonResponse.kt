package com.example.data.model

import com.google.gson.annotations.SerializedName

data class LatestPassedLessonResponse(

    @field:SerializedName("latestLessonName")
    val latestLessonName: String? = null,

    @field:SerializedName("studentID")
    val studentID: Int? = null,

    @field:SerializedName("latestPassedLessonID")
    val latestPassedLessonID: Int? = null,

    @field:SerializedName("student")
    val student: Student? = null,

    @field:SerializedName("course")
    val course: Course? = null,

    @field:SerializedName("courseID")
    val courseID: Int? = null
)
