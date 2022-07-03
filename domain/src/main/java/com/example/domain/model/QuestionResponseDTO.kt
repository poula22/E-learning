package com.example.domain.model

data class QuestionResponseDTO(
	val quizId: Int? = null,
	val id: Int? = null,
	var title: String? = null,
	var correctAnswer: String? = null,
	val showDate: String? = null
)
