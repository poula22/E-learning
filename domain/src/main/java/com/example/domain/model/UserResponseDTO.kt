package com.example.domain.model

data class UserResponseDTO(
	val firstName: String? = null,
	val lastName: String? = null,
	val emailAddress: String? = null,
	val password: String? = null,
	val role: String? = null,
	val phone: String? = null,
	val profilePic: String? = null,
	val id: Int? = null
)
