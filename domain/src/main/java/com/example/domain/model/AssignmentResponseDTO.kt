package com.example.domain.model

import com.google.gson.annotations.SerializedName

data class AssignmentResponseDTO(
	val filePath: String? = null,
	val grade: Int? = null,
	val description: String? = null,
	val id: Int? = null,
	val endTime: String? = null,
	val title: String? = null,
	val courseId: Int? = null,
	val startDate: String? = null
)
