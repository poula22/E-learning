package com.example.lamp.ui.student.student_course_page.course_content.quiz.answers_recycler_view

import com.example.domain.model.QuestionChoiceResponseDTO

data class QuestionChoiceItem(var questionChoiceResponseDTO: QuestionChoiceResponseDTO?,var isSelected:Boolean=false)