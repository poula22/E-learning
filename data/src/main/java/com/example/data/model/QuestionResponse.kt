package com.example.data.model

import com.google.gson.annotations.SerializedName

data class QuestionResponse(

	@field:SerializedName("quizId")
	val quizId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	var title: String? = null,

	@field:SerializedName("correctAnswer")
	var correctAnswer: String? = null,

	@field:SerializedName("showDate")
	var showDate: String? = null
)
