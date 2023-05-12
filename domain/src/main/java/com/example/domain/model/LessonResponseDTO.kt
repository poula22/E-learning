package com.example.domain.model

import java.io.Serializable

data class LessonResponseDTO(
	val description: String? = null,
	val id: Int? = null,
	val title: String? = null,
	val courseId: Int? = null
) : Serializable
