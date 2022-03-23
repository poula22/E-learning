package com.example.data.repos

import com.example.data.api.CoursesWebService
import com.example.domain.model.CourseDTO
import com.example.domain.repos.CoursesOnlineDataSource

class CoursesOnlineDataSourceImp(coursesWebService: CoursesWebService):CoursesOnlineDataSource {
    override suspend fun getCoursesById(): List<CourseDTO> {
        TODO("Not yet implemented")
    }
}