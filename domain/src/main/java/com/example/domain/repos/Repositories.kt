package com.example.domain.repos

import com.example.domain.model.ChildDTO
import com.example.domain.model.CourseDTO
import com.example.domain.model.FeatureDTO
import com.example.domain.model.GradeDTO

interface CoursesRepository{
    suspend fun getCourses():List<CourseDTO>
}

interface CoursesOnlineDataSource{
    suspend fun getCoursesById():List<CourseDTO>
}

interface FeaturesRepository{
    suspend fun getGrades():List<FeatureDTO>
}

interface FeaturesOnlineDataSource{
    suspend fun getCoursesById():List<FeatureDTO>
}

interface ChildrenRepository{
    suspend fun getGrades():List<ChildDTO>
}

interface ChildrenOnlineDataSource{
    suspend fun getChildren():List<ChildDTO>
}
