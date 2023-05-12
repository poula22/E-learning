package com.example.data.model

import com.google.gson.annotations.SerializedName

data class ContentResponse(

	@field:SerializedName("videoPath")
	val videoPath: String? = null,

	@field:SerializedName("pdfPath")
	val pdfPath: String? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("lessonId")
	val lessonId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("showDate")
	val showDate: String? = null
)
