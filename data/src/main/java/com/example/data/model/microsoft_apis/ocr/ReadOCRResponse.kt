package com.example.data.model.microsoft_apis.ocr

import com.google.gson.annotations.SerializedName

data class ReadOCRResponse(

	@field:SerializedName("analyzeResult")
	val analyzeResult: AnalyzeResult? = null,

	@field:SerializedName("createdDateTime")
	val createdDateTime: String? = null,

	@field:SerializedName("lastUpdatedDateTime")
	val lastUpdatedDateTime: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class LinesItemRead(

	@field:SerializedName("boundingBox")
	val boundingBox: List<Int?>? = null,

	@field:SerializedName("appearance")
	val appearance: Appearance? = null,

	@field:SerializedName("words")
	val words: List<WordsItemRead?>? = null,

	@field:SerializedName("text")
	val text: String? = null
)

data class ReadResultsItemRead(

	@field:SerializedName("unit")
	val unit: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("angle")
	val angle: Double? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("lines")
	val lines: List<LinesItemRead?>? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class Appearance(

	@field:SerializedName("styleConfidence")
	val styleConfidence: Double? = null,

	@field:SerializedName("style")
	val style: String? = null
)

data class AnalyzeResult(

	@field:SerializedName("modelVersion")
	val modelVersion: String? = null,

	@field:SerializedName("readResults")
	val readResults: List<ReadResultsItemRead?>? = null,

	@field:SerializedName("version")
	val version: String? = null
)

data class WordsItemRead(

	@field:SerializedName("boundingBox")
	val boundingBox: List<Int?>? = null,

	@field:SerializedName("confidence")
	val confidence: Double? = null,

	@field:SerializedName("text")
	val text: String? = null
)
