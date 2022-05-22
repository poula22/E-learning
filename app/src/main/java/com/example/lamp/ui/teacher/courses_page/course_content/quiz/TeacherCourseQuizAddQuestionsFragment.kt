package com.example.lamp.ui.teacher.courses_page.course_content.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseQuizAddQuestionsBinding

class TeacherCourseQuizAddQuestionsFragment : Fragment() {

    lateinit var viewBinding: FragmentTeacherCourseQuizAddQuestionsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_course_quiz_add_questions,
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
        viewBinding.createQuestionButton.setOnClickListener {
            createQuestion()
        }
        viewBinding.createQuestionFab.setOnClickListener {

        }
    }

    private fun createQuestion() {
        TODO("Not yet implemented")
    }


}