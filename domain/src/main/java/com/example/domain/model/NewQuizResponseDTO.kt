package com.example.domain.model

import com.google.gson.annotations.SerializedName

data class NewQuizResponseDTO(
    val quizId: Int? = null,
    val totalPoints: Int? = null,
    val assignedGrade: Int? = null,
    val title: String? = null
)