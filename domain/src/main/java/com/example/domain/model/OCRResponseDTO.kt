package com.example.domain.model

data class OCRResponseDTO (

        val orientation: String? = null,

        val regions: List<RegionsItemDTO?>? = null,

        val modelVersion: String? = null,

        val textAngle: Double? = null,

        val language: String? = null,

        val code: String? = null,

        val message: String? = null
    )

    data class RegionsItemDTO(

        val boundingBox: String? = null,

        val lines: List<LinesItemDTO?>? = null
    )

    data class LinesItemDTO(

        val boundingBox: String? = null,

        val words: List<WordsItemDTO?>? = null
    )

    data class WordsItemDTO(
        val boundingBox: String? = null,

        val text: String? = null
    )
