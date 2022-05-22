package com.example.lamp.ui.teacher.courses_page.course_content.quiz.questions_recycler_view

data class QuestionItem(
    val quizId : Int,
    val questionId : Int,
    val question : String,
    var answers : HashMap<String,Boolean>?=null,

    )
