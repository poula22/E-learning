package com.example.lamp.ui.student.student_course_page.course_content.assignment

import java.util.*

data class AssignmentItem(
    var title: String,
    var description:String,
    var startDate:Date,
    var deadline: Date,
    var state: String,
    var grade: Int
)
