package com.example.data.model

import com.google.gson.annotations.SerializedName

data class AssignmentGradeResponse(

	@field:SerializedName("AssignmentGradeResponse")
	val assignmentGradeResponse: List<AssignmentGradeResponseItem?>? = null
)

data class AssignmentGradeResponseItem(

	@field:SerializedName("assignmentAnswerID")
	val assignmentAnswerID: Int? = null,

	@field:SerializedName("assignmentAnswer")
	val assignmentAnswer: AssignmentAnswer? = null,

	@field:SerializedName("grade")
	val grade: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null
)


