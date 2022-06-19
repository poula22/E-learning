package com.example.data.model

import com.google.gson.annotations.SerializedName

data class AssignmentAnswerResponse(

	@field:SerializedName("studentId")
	val studentId: Int? = null,

	@field:SerializedName("fileName")
	val fileName: String? = null,

	@field:SerializedName("pdf")
	val pdf: String? = null,

	@field:SerializedName("submitTime")
	val submitTime: String? = null,

	@field:SerializedName("submitDate")
	val submitDate: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("assignmentId")
	val assignmentId: Int? = null
)
