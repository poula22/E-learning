package com.example.lamp.ui.student.student_course_page.course_content.quiz.quizzes_recycler_view

import com.example.domain.model.QuizResponseDTO

data class StudentQuizItem(var quizResponseDTO: QuizResponseDTO,var duration:String?=null)
