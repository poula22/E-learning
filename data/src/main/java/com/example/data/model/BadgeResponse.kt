package com.example.data.model

import com.google.gson.annotations.SerializedName


data class BadgeResponseItem(

	@field:SerializedName("assignmentAnswerID")
	val assignmentAnswerID: Int? = null,

	@field:SerializedName("assignmentAnswer")
	val assignmentAnswer: AssignmentAnswer? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null
)

