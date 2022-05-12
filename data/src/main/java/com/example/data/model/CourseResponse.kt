package com.example.data.model

import com.google.gson.annotations.SerializedName

data class CourseResponse(

    @field:SerializedName("courseName")
    val courseName: String? = null,

    @field:SerializedName("courseImage")
    val courseImage: String? = null,

    @field:SerializedName("teacherID")
    val teacherID: Int? = null,

    @field:SerializedName("teacher")
    val teacher: Teacher? = null,

    @field:SerializedName("assignments")
    val assignments: List<Any?>? = null,

    @field:SerializedName("students")
    val students: List<Any?>? = null,

    @field:SerializedName("quizzes")
    val quizzes: List<Any?>? = null,

    @field:SerializedName("courseID")
    val courseID: Int? = null,

    @field:SerializedName("courseDescription")
    val courseDescription: String? = null,

    @field:SerializedName("announcements")
    val announcements: List<Any?>? = null,

    @field:SerializedName("lessons")
    val lessons: List<Any?>? = null,

    @field:SerializedName("latestPassedLessons")
    val latestPassedLessons: List<Any?>? = null
)

data class Teacher(
    val any: Any? = null
)
