package com.example.lamp.ui.student.student_course_page.course_content.assignment

data class AssignmentToTeacherItem(
    val studentName: String,
    val fileName: String?,
    val filePath: String?,
    val assignmentNote: String?,
    var studentGrade: Int,
    val totalPoints: Int
)
