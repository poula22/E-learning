package com.example.domain.model

data class UserResponseDTO(
	val firstName: String? = null,
	val lastName: String? = null,
	val emailAddress: String? = null,
	val password: String? = null,
	val role: String? = null,
	val phone: String? = null,
	val profilePic: String? = null,
	val id: Int? = null,
	val refreshTokenExpiration: String? = null,
	val isAuthenticated: Boolean? = null,
	val message: Any? = null,
	val token: String? = null,
	val expiresOn: String? = null,
	val refreshToken: String? = null
	)
