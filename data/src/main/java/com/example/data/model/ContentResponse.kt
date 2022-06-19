package com.example.data.model

import com.google.gson.annotations.SerializedName

data class ContentResponse(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("fileName")
	val fileName: String? = null,

	@field:SerializedName("lessonId")
	val lessonId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("showDate")
	val showDate: String? = null
)
