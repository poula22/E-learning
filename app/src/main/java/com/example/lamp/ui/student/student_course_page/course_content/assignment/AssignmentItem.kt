package com.example.lamp.ui.student.student_course_page.course_content.assignment

import java.util.*

data class AssignmentItem(
    val title: String,
    val deadline: Date,
    val state: String,
    val grade: Int
)
