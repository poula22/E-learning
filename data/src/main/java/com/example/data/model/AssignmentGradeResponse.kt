package com.example.data.model

import com.google.gson.annotations.SerializedName

data class AssignmentGradeResponse(

	@field:SerializedName("assignmentAnswerId")
	val assignmentAnswerId: Int? = null,

	@field:SerializedName("grade")
	val grade: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
