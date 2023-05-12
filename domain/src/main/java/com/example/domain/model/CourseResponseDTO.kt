package com.example.domain.model

import java.io.Serializable

data class CourseResponseDTO(
	val courseName: String? = null,
	val courseImage: String? = null,
	val teacherId: Int? = null,
	val id: Int? = null,
	val courseDescription: String? = null
):Serializable
