package com.example.data.model

import com.google.gson.annotations.SerializedName

data class QuizGradeResponse(

	@field:SerializedName("QuizGradeResponse")
	val quizGradeResponse: List<QuizGradeResponseItem?>? = null
)

data class QuizAnswer(
	val any: Any? = null
)

data class QuizGradeResponseItem(

	@field:SerializedName("quizAnswerID")
	val quizAnswerID: Int? = null,

	@field:SerializedName("grade")
	val grade: Int? = null,

	@field:SerializedName("quizAnswer")
	val quizAnswer: QuizAnswer? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
