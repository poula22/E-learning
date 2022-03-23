package com.example.data.repos

import com.example.data.api.FeatureWebService
import com.example.domain.model.FeatureDTO
import com.example.domain.repos.FeaturesOnlineDataSource

class FeatureOnlineDataSourceImp(featureWebService: FeatureWebService):FeaturesOnlineDataSource {
    override suspend fun getCoursesById(): List<FeatureDTO> {
        TODO("Not yet implemented")
    }
}