package com.example.lamp.ui.teacher.courses_page.course_content.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.databinding.FragmentTeacherCourseQuizzesBinding

class TeacherCourseQuizzesFragment : Fragment() {

    lateinit var viewBinding: FragmentTeacherCourseQuizzesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            com.example.lamp.R.layout.fragment_teacher_course_quizzes,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewBinding.createFab.setOnClickListener {
            createQuiz()
        }

        viewBinding.createQuizButton.setOnClickListener {
            createQuiz()
        }

    }

    private fun createQuiz() {

    }



}