package com.example.data.model

import com.google.gson.annotations.SerializedName

data class QuestionChoiceResponse(

	@field:SerializedName("questionId")
	val questionId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("choice")
	val choice: String? = null
)
