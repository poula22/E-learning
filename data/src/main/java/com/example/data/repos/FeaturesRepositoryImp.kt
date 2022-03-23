package com.example.data.repos

import com.example.domain.model.FeatureDTO
import com.example.domain.repos.FeaturesOnlineDataSource
import com.example.domain.repos.FeaturesRepository

class FeaturesRepositoryImp(featuresOnlineDataSource: FeaturesOnlineDataSource):FeaturesRepository {
    override suspend fun getGrades(): List<FeatureDTO> {
        TODO("Not yet implemented")
    }
}