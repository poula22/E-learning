package com.example.lamp.ui.teacher.courses_page.course_content.quiz.answers_recycler_view

import com.example.domain.model.QuestionChoiceResponseDTO

data class AnswerItem(val questionChoiceResponseDTO: QuestionChoiceResponseDTO?=QuestionChoiceResponseDTO(), var isCorrect:Boolean=false)
