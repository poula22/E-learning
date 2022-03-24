package com.example.data.repos

import com.example.data.api.ChildrenWebService
import com.example.domain.model.ChildDTO
import com.example.domain.repos.ChildrenOnlineDataSource

class ChildrenOnlineDataSourcesImp(childrenWebService: ChildrenWebService):ChildrenOnlineDataSource {
    override suspend fun getChildren(): List<ChildDTO> {
        TODO("Not yet implemented")
    }
}