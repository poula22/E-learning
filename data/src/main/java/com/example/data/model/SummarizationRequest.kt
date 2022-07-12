package com.example.data.model

import com.google.gson.annotations.SerializedName

data class SummarizationTextRequest(

	@field:SerializedName("sentnum")
	val sentnum: String? = null,

	@field:SerializedName("text")
	val text: String? = null
)


data class SummarizationUrlRequest(

	@field:SerializedName("sentnum")
	val sentnum: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)