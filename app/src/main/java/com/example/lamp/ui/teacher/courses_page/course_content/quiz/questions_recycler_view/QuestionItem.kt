package com.example.lamp.ui.teacher.courses_page.course_content.quiz.questions_recycler_view

import com.example.lamp.ui.teacher.courses_page.course_content.quiz.answers_recycler_view.AnswerItem

data class QuestionItem(
    val questionId : Int?,
    var question : String?,
    var answers : MutableList<AnswerItem>?
    )
