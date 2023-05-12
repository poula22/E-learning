package com.example.domain.model

data class TeacherQuestionWithChoicesResponseDTO(
    val quizId: Int? = null,
    val questionChoices: List<QuestionChoiceResponseDTO?>? = null,
    val id: Int? = null,
    val title: String? = null,
    val correctAnswer: String? = null,
    val showDate: String? = null
)
