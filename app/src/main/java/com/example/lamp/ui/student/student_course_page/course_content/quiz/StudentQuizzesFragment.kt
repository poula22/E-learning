package com.example.lamp.ui.student.student_course_page.course_content.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCourseQuizzesBinding
import com.example.lamp.ui.student.student_course_page.course_content.quiz.quizzes_recycler_view.StudentQuizAdapter
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.quizzes_recycler_view.QuizItem

class StudentQuizzesFragment (var quizzes:MutableList<QuizItem>?=null): Fragment() {
    lateinit var viewBinding: FragmentStudentCourseQuizzesBinding
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
        val adapter= StudentQuizAdapter()
        viewBinding.createDraftRecycler.adapter = adapter

        adapter.onStartExamListener=object :StudentQuizAdapter.OnStartExamListener{
            override fun onStartExam(quiz:QuizItem) {
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.student_course_content_container
                        ,StudentQuizFragment(quiz))
                    .commit()
            }

        }

    }

    override fun onStart() {
        super.onStart()
        var toolbar: Toolbar =requireActivity().findViewById(R.id.toolbar)
        toolbar.isVisible=true
        var drawerLayout: DrawerLayout =requireActivity().findViewById(R.id.drawer_layout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }




}