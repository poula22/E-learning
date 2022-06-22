package com.example.data.model

import com.google.gson.annotations.SerializedName

data class AssignmentAnswerDetailsResponse(

	@field:SerializedName("studentLastName")
	val studentLastName: String? = null,

	@field:SerializedName("studentId")
	val studentId: Int? = null,

	@field:SerializedName("fileName")
	val fileName: String? = null,

	@field:SerializedName("pdf")
	val pdf: String? = null,

	@field:SerializedName("submitDate")
	val submitDate: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("studentFirstName")
	val studentFirstName: String? = null,

	@field:SerializedName("assignmentId")
	val assignmentId: Int? = null
)
