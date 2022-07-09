package com.example.data.model

import com.google.gson.annotations.SerializedName

data class QuizResponse(

	@field:SerializedName("instructions")
	val instructions: String? = null,

	@field:SerializedName("totalPoints")
	val grade: Int? = null,

	@field:SerializedName("startTime")
	val startTime: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("endTime")
	val endTime: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("courseId")
	val courseId: Int? = null
)
