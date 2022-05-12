package com.example.data.repos

import com.example.data.api.CourseWebService
import com.example.domain.model.CourseDTO
import com.example.domain.repos.CoursesOnlineDataSource

class CoursesOnlineDataSourceImp(coursesWebService: CourseWebService):CoursesOnlineDataSource {
    override suspend fun getCoursesById(): List<CourseDTO> {
        TODO("Not yet implemented")
    }
}