package com.example.lamp.ui.student.student_course_page.course_content.quiz.quizzes_recycler_view

import com.example.domain.model.QuestionChoiceResponseDTO
import com.example.domain.model.QuestionResponseDTO
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.answers_recycler_view.AnswerItem

data class QuestionItem (var question: QuestionResponseDTO?=null,
                         var answers: MutableList<AnswerItem>?=null)