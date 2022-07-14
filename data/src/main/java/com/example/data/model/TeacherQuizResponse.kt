package com.example.data.model

import com.google.gson.annotations.SerializedName

data class TeacherQuizResponse(

	@field:SerializedName("instructions")
	val instructions: String? = null,

	@field:SerializedName("postTime")
	val postTime: String? = null,

	@field:SerializedName("totalPoints")
	val totalPoints: Int? = null,

	@field:SerializedName("questions")
	val questions: List<TeacherQuestionWithChoicesResponse?>? = null,

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