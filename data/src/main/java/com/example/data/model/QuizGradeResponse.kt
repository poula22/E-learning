package com.example.data.model

import com.google.gson.annotations.SerializedName

data class QuizGradeResponse(

	@field:SerializedName("studentId")
	val studentId: Int? = null,

	@field:SerializedName("quizId")
	val quizId: Int? = null,

	@field:SerializedName("grade")
	val grade: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
