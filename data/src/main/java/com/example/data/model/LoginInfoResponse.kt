package com.example.data.model

import com.google.gson.annotations.SerializedName


data class LoginInfoResponse(

    @field:SerializedName("emailAddress")
    val emailAddress: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("toDoLists")
    val toDoLists: List<Any?>? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("type")
    val type: Int? = null
)
