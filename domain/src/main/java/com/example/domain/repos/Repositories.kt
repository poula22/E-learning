package com.example.domain.repos

import com.example.domain.model.*
import kotlinx.coroutines.CoroutineScope

interface CoursesRepository{
    suspend fun getCourses():List<CourseDTO>
}

interface CoursesOnlineDataSource{
    suspend fun getCoursesById():List<CourseDTO>
}

interface OCROnlineDataSource{
    suspend fun getTextFromImage(language:String,url:String): OCRResponseDTO
    suspend fun getTextFromImageReadApi(language:String?=null,url:String): ReadOCRResponseDTO
}

interface OCRRepository{
    suspend fun getTextFromImage(language:String,url:String):OCRResponseDTO
    suspend fun getTextFromImageReadApi(language: String?=null, url: String): ReadOCRResponseDTO
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
