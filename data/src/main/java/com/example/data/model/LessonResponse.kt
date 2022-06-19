package com.example.data.model

import com.google.gson.annotations.SerializedName

data class LessonResponse(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("courseId")
	val courseId: Int? = null
)
