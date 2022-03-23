package com.example.domain.model

data class FeaturesResponseDTO(
    val feature: FeatureDTO?=null
)
data class FeatureDTO(
    val name:String?=null
)