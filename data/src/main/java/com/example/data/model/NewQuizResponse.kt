package com.example.data.model

import com.google.gson.annotations.SerializedName

data class NewQuizResponse(

	@field:SerializedName("quizId")
	val quizId: Int? = null,

	@field:SerializedName("totalPoints")
	val totalPoints: Int? = null,

	@field:SerializedName("assignedGrade")
	val assignedGrade: Int? = null,

	@field:SerializedName("title")
	val title: String? = null
)
