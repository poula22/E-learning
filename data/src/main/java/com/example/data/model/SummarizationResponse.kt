package com.example.data.model

import com.google.gson.annotations.SerializedName

data class SummarizationResponse(

	@field:SerializedName("sentences")
	val sentences: List<String?>? = null
)
