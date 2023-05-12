package com.example.domain.model

data class TeacherResponseDTO(
	val firstName: String? = null,
	val lastName: String? = null,
	val emailAddress: String? = null,
	val password: String? = null,
	val role: String? = null,
	val phone: String? = null,
	var profilePic: String? = null,
	val id: Int? = null
)
