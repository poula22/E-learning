package com.example.lamp.ui.student.student_course_page.course_content.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.domain.model.QuestionChoiceResponseDTO
import com.example.domain.model.QuizDetailsResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentQuizFinishedStatsBinding

class FragmentStudentQuizFinishedStats(
    var studentAnswers: MutableList<QuestionChoiceResponseDTO>,
    var quiz:List<QuizDetailsResponseDTO>):Fragment() {
    lateinit var viewBinding: FragmentStudentQuizFinishedStatsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_student_course_quizzes, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        //student_course_content_container
    }
}