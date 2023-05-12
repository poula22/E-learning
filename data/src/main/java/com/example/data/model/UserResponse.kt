package com.example.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("emailAddress")
	val emailAddress: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("profilePic")
	val profilePic: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,


	@field:SerializedName("refreshTokenExpiration")
	val refreshTokenExpiration: String? = null,

	@field:SerializedName("isAuthenticated")
	val isAuthenticated: Boolean? = null,

	@field:SerializedName("message")
	val message: Any? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("expiresOn")
	val expiresOn: String? = null,

	@field:SerializedName("refreshToken")
	val refreshToken: String? = null
)
