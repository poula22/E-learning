package com.example.domain.model

data class QuizResponseDTO(
	val instructions: String? = null,
	val postTime: String? = null,
	val grade: Int? = null,
	val startTime: String? = null,
	val id: Int? = null,
	val endTime: String? = null,
	val title: String? = null,
	val courseId: Int? = null
)
