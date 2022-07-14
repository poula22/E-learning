package com.example.domain.model


data class AssignmentAnswerResponseDTO(

    val studentId: Int? = null,
    var fileName: String? = null,
    val pdf: String? = null,
    val submitTime: String? = null,
    val submitDate: String? = null,
    val id: Int? = null,
    val assignmentId: Int? = null,
    val grade:Int?=null
)
