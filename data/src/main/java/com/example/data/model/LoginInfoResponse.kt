package com.example.data.model

data class LoginInfoResponse (
    val loginInfo: LoginInfo?=null
        )
data class LoginInfo(
    val name:String?=null,
    val pass:String?=null
)