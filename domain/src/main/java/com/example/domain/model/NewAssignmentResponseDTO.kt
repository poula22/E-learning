package com.example.domain.model

data class NewAssignmentResponseDTO(
    val submitted: Boolean? = null,
    val totalPoints: Int? = null,
    val assignedGrade: Int? = null,
    val title: String? = null,
    val assignmentId: Int? = null
)