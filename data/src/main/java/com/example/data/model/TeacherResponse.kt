package com.example.data.model

import com.google.gson.annotations.SerializedName


data class TeacherResponse(

    @field:SerializedName("courses")
    val courses: List<Any?>? = null,

    @field:SerializedName("emailAddress")
    val emailAddress: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("profilePic")
    val profilePic: String? = null,

    @field:SerializedName("teacherLastName")
    val teacherLastName: String? = null,

    @field:SerializedName("teacherPhone")
    val teacherPhone: String? = null,

    @field:SerializedName("toDoLists")
    val toDoLists: List<Any?>? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("type")
    val type: Int? = null,

    @field:SerializedName("teacherFirstName")
    val teacherFirstName: String? = null
)
