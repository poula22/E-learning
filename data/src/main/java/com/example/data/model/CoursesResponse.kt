package com.example.data.model

data class CoursesResponse(
    var course: Course?=null
)
data class Course(
    var name:String?=null
)
data class Grade(
    var mark:String?=null
)
data class Parent(
    val name:String?=null
)
data class Student(
    val name:String?=null
)