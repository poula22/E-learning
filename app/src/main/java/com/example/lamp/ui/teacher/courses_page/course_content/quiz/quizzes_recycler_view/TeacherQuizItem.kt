package com.example.lamp.ui.teacher.courses_page.course_content.quiz.quizzes_recycler_view

import com.example.lamp.ui.teacher.courses_page.course_content.quiz.questions_recycler_view.QuestionItem

data class TeacherQuizItem(
    val quizId: Int?,
    val questions: MutableList<QuestionItem>?,
    val quizName: String?,
    val instructions : String?
)
