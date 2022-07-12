package com.example.domain.model

data class RecitationParagraphResponseDTO(
    val resultMsg: String? = null,
    val author: String? = null,
    val similarity: Double? = null,
    val resultCode: Int? = null,
    val value: Double? = null,
    val version: String? = null,
    val email: String? = null
)