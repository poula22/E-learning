package com.example.data.model

import com.google.gson.annotations.SerializedName

data class TeacherQuestionWithChoicesResponse(

	@field:SerializedName("quizId")
	val quizId: Int? = null,

	@field:SerializedName("questionChoices")
	val questionChoices: List<QuestionChoiceResponse?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("correctAnswer")
	val correctAnswer: String? = null,

	@field:SerializedName("showDate")
	val showDate: String? = null
)

