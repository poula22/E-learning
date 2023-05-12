package com.example.domain.model

import com.google.gson.annotations.SerializedName

data class TeacherQuizResponseDTO(
    val instructions: String? = null,
    val postTime: String? = null,
    val totalPoints: Int? = null,
    val questions: List<TeacherQuestionWithChoicesResponseDTO?>? = null,
    val startTime: String? = null,
    val id: Int? = null,
    val endTime: String? = null,
    val title: String? = null,
    val courseId: Int? = null
)