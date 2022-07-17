package com.example.data.model

import com.google.gson.annotations.SerializedName

data class NewAssignmentResponse(

	@field:SerializedName("submitted")
	val submitted: Boolean? = null,

	@field:SerializedName("totalPoints")
	val totalPoints: Int? = null,

	@field:SerializedName("assignedGrade")
	val assignedGrade: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("assignmentId")
	val assignmentId: Int? = null
)
