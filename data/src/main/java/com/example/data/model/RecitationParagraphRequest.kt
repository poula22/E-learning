package com.example.data.model

import com.google.gson.annotations.SerializedName

data class RecitationParagraphRequest(

	@field:SerializedName("text1")
	val text1: String? = null,

	@field:SerializedName("text2")
	val text2: String? = null
)
