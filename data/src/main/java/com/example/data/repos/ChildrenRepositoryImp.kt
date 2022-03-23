package com.example.data.repos

import com.example.domain.model.ChildDTO
import com.example.domain.repos.ChildrenOnlineDataSource
import com.example.domain.repos.ChildrenRepository

class ChildrenRepositoryImp(childrenOnlineDataSource: ChildrenOnlineDataSource):ChildrenRepository {
    override suspend fun getGrades(): List<ChildDTO> {
        TODO("Not yet implemented")
    }
}