package com.example.lamp.ui.teacher.courses_page.course_content.quiz.quizzes_recycler_view

import com.example.lamp.ui.teacher.courses_page.course_content.quiz.questions_recycler_view.QuestionItem
import java.util.*

data class QuizItem(
    val quizId: Int?,
    val questions: MutableList<QuestionItem>?,
    val quizName: String?,
    val instructions : String?,
    val point:Int?,
    val durationMinutes:Int?,
    val deadLine:Date?
)
