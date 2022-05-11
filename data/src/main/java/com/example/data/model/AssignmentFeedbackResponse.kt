package com.example.data.model

import com.google.gson.annotations.SerializedName

data class AssignmentFeedbackResponse(

	@field:SerializedName("AssignmentFeedbackResponse")
	val assignmentFeedbackResponse: List<AssignmentFeedbackResponseItem?>? = null
)

data class AssignmentFeedbackResponseItem(

	@field:SerializedName("feedback")
	val feedback: String? = null,

	@field:SerializedName("assignmentAnswerID")
	val assignmentAnswerID: Int? = null,

	@field:SerializedName("assignmentAnswer")
	val assignmentAnswer: AssignmentAnswer? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

