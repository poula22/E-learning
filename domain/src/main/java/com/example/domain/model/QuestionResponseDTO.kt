package com.example.domain.model

data class QuestionResponseDTO(
	var quizId: Int? = null,
	var id: Int? = null,
	var title: String? = null,
	var correctAnswer: String? = null,
	val showDate: String? = null
)
