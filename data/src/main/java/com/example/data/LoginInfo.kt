package com.example.data

import com.google.gson.annotations.SerializedName

data class LoginInfo(
    val emailAddress: String? = null,
    val password: String? = null,
    val toDoLists: List<Any?>? = null,
    val id: Int? = null,
    val type: Int? = null
)
