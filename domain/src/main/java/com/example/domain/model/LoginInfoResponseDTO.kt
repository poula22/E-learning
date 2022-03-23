package com.example.domain.model

data class LoginInfoResponseDTO (
    val loginInfo: LoginInfoDTO?=null
        )
data class LoginInfoDTO(
    val name:String?=null,
    val pass:String?=null
)