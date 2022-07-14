package com.example.data.model

import com.google.gson.annotations.SerializedName

data class ParentChildCoursesResponse(

    @field:SerializedName("teacherEmailAddress")
    val teacherEmailAddress: String? = null,

    @field:SerializedName("courseName")
    val courseName: String? = null,

    @field:SerializedName("teacherId")
    val teacherId: Int? = null,

    @field:SerializedName("courseImage")
    val courseImage: String? = null,

    @field:SerializedName("teacherLastName")
    val teacherLastName: String? = null,

    @field:SerializedName("teacherPhone")
    val teacherPhone: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("teacherFirstName")
    val teacherFirstName: String? = null,

    @field:SerializedName("teacherProfilePic")
    val teacherProfilePic: String? = null,

    @field:SerializedName("courseDescription")
    val courseDescription: String? = null
)
