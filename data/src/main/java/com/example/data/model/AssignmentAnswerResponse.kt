package com.example.data.model

import com.google.gson.annotations.SerializedName

data class AssignmentAnswerResponse(

	@field:SerializedName("AssignmentAnswerResponse")
	val assignmentAnswerResponse: List<AssignmentAnswerResponseItem?>? = null
)

data class AssignmentFeedback(
	val any: Any? = null
)

data class AssignmentGrade(
	val any: Any? = null
)

data class Badge(
	val any: Any? = null
)

data class AssignmentAnswerResponseItem(

	@field:SerializedName("studentID")
	val studentID: Int? = null,

	@field:SerializedName("assignmentFeedback")
	val assignmentFeedback: AssignmentFeedback? = null,

	@field:SerializedName("badge")
	val badge: Badge? = null,

	@field:SerializedName("fileName")
	val fileName: String? = null,

	@field:SerializedName("pdf")
	val pdf: String? = null,

	@field:SerializedName("submitTime")
	val submitTime: String? = null,

	@field:SerializedName("student")
	val student: Student? = null,

	@field:SerializedName("assignment")
	val assignment: Assignment? = null,

	@field:SerializedName("submitDate")
	val submitDate: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("assignmentID")
	val assignmentID: Int? = null,

	@field:SerializedName("assignmentGrade")
	val assignmentGrade: AssignmentGrade? = null
)



data class Assignment(
	val any: Any? = null
)
