package com.example.data.model

import com.google.gson.annotations.SerializedName

data class RecitationParagraphResponse(

	@field:SerializedName("result_msg")
	val resultMsg: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("similarity")
	val similarity: Double? = null,

	@field:SerializedName("result_code")
	val resultCode: Int? = null,

	@field:SerializedName("value")
	val value: Double? = null,

	@field:SerializedName("version")
	val version: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
