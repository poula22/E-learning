package com.example.data.model

import com.google.gson.annotations.SerializedName

data class QuestionAnswerResponse(

	@field:SerializedName("studentId")
	val studentId: Int? = null,

	@field:SerializedName("questionId")
	val questionId: Int? = null,

	@field:SerializedName("answer")
	val answer: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
