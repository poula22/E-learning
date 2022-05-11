package com.example.data.model

import com.google.gson.annotations.SerializedName

data class LessonResponse(

	@field:SerializedName("LessonResponse")
	val lessonResponse: List<LessonResponseItem?>? = null
)



data class LessonResponseItem(

	@field:SerializedName("notes")
	val notes: List<Any?>? = null,

	@field:SerializedName("contents")
	val contents: List<Any?>? = null,

	@field:SerializedName("questions")
	val questions: List<Any?>? = null,

	@field:SerializedName("lessonID")
	val lessonID: Int? = null,

	@field:SerializedName("course")
	val course: Course? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("courseID")
	val courseID: Int? = null
)
