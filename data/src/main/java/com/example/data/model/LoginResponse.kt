package com.example.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("emailAddress")
	val emailAddress: String? = null,

	@field:SerializedName("password")
	val password: String? = null
)
