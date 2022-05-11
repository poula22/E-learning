package com.example.data.model

import com.google.gson.annotations.SerializedName

data class QuestionResponse(

	@field:SerializedName("QuestionResponse")
	val questionResponse: List<QuestionResponseItem?>? = null
)


data class QuestionResponseItem(

	@field:SerializedName("questionID")
	val questionID: Int? = null,

	@field:SerializedName("questionTitle")
	val questionTitle: String? = null,

	@field:SerializedName("questionAnswers")
	val questionAnswers: List<Any?>? = null,

	@field:SerializedName("lastchoise")
	val lastchoise: String? = null,

	@field:SerializedName("lesson")
	val lesson: Lesson? = null,

	@field:SerializedName("lessonID")
	val lessonID: Int? = null,

	@field:SerializedName("correctAnswer")
	val correctAnswer: String? = null,

	@field:SerializedName("secondchoise")
	val secondchoise: String? = null,

	@field:SerializedName("showDate")
	val showDate: String? = null,

	@field:SerializedName("firstchoise")
	val firstchoise: String? = null
)
