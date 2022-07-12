package com.example.domain.model

data class SummarizationTextRequestDTO(
    val sentnum: String? = null,
    val text: String? = null
)


data class SummarizationUrlRequestDTO(
    val sentnum: String? = null,
    val url: String? = null
)