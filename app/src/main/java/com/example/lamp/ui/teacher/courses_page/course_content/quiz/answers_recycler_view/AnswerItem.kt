package com.example.lamp.ui.teacher.courses_page.course_content.quiz.answers_recycler_view

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.data.model.QuestionChoiceResponse
import com.example.data.model.QuestionResponse

data class AnswerItem(var questionChoiceResponse: QuestionChoiceResponse?=QuestionChoiceResponse(), var isCorrect:Boolean=false)
