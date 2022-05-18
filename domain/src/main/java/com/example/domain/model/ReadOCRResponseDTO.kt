package com.example.domain.model

data class ReadOCRResponseDTO(

    val analyzeResult: AnalyzeResultDTO? = null,

    val createdDateTime: String? = null,

    val lastUpdatedDateTime: String? = null,

    val status: String? = null
)

data class LinesItemReadDTO(

    val boundingBox: List<Int?>? = null,

    val appearance: AppearanceDTO? = null,

    val words: List<WordsItemReadDTO?>? = null,

    val text: String? = null
)

data class ReadResultsItemReadDTO(
    val unit: String? = null,

    val width: Int? = null,

    val angle: Double? = null,

    val page: Int? = null,

    val lines: List<LinesItemReadDTO?>? = null,

    val height: Int? = null
)

data class AppearanceDTO(


    val styleConfidence: Double? = null,

    val style: String? = null
)

data class AnalyzeResultDTO(

    val modelVersion: String? = null,

    val readResults: List<ReadResultsItemReadDTO?>? = null,

    val version: String? = null
)

data class WordsItemReadDTO(

    val boundingBox: List<Int?>? = null,

    val confidence: Double? = null,

    val text: String? = null
)

