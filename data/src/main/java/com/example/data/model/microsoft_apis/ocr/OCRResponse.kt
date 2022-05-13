package com.example.data.model.microsoft_apis.ocr

import com.example.domain.model.RegionsItemDTO
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class OCRResponse(

	@field:SerializedName("orientation")
	val orientation: String? = null,

	@field:SerializedName("regions")
	val regions: List<RegionsItem?>? = null,

	@field:SerializedName("modelVersion")
	val modelVersion: String? = null,

	@field:SerializedName("textAngle")
	val textAngle: Double? = null,

	@field:SerializedName("language")
	val language: String? = null,
	@field:SerializedName("code")
	val code: String? = null,
	@field:SerializedName("message")
	val message: String? = null
)


data class RegionsItem(

	@field:SerializedName("boundingBox")
	val boundingBox: String? = null,

	@field:SerializedName("lines")
	val lines: List<LinesItem?>? = null
)

data class LinesItem(

	@field:SerializedName("boundingBox")
	val boundingBox: String? = null,

	@field:SerializedName("words")
	val words: List<WordsItem?>? = null
)


data class WordsItem(

	@field:SerializedName("boundingBox")
	val boundingBox: String? = null,

	@field:SerializedName("text")
	val text: String? = null
)
