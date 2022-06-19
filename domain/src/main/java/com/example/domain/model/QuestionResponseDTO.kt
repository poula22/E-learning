package com.example.domain.model

data class QuestionResponseDTO(
	val quizId: Int? = null,
	val id: Int? = null,
	val title: String? = null,
	val correctAnswer: String? = null,
	val showDate: String? = null
)
