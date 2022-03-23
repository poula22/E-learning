package com.example.domain.model

data class CoursesResponseDTO(
    var courseDTO: CourseDTO?=null
)
data class CourseDTO(
    var name:String?=null
)
data class GradeDTO(
    var mark:String?=null
)
data class ParentDTO(
    val name:String?=null
)
data class StudentDTO(
    val name:String?=null
)