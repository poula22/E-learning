package com.example.data.repos

import com.example.domain.model.CourseDTO
import com.example.domain.repos.CoursesOnlineDataSource
import com.example.domain.repos.CoursesRepository

class CoursesRepositoryImp(coursesOnlineDataSource: CoursesOnlineDataSource):CoursesRepository {
    override suspend fun getCourses(): List<CourseDTO> {
        TODO("Not yet implemented")
    }
}