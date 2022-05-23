package com.example.lamp.ui.teacher.courses_page.course_content.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseQuizzesBinding
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.quizzes_recycler_view.TeacherQuizAdapter
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.quizzes_recycler_view.TeacherQuizItem

class TeacherCourseQuizzesFragment(var quizzes:MutableList<TeacherQuizItem>?=null) : Fragment() {

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
        val adapter=TeacherQuizAdapter()
        adapter.onEditQuizListener=object :TeacherQuizAdapter.OnEditQuizListener{
            override fun onEditQuiz(quiz:TeacherQuizItem) {
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.teacher_course_container
                    ,TeacherCourseQuizAddQuestionsFragment(quiz))
                    .commit()
            }

        }
        viewBinding.createDraftRecycler.adapter=adapter

    }

    private fun createQuiz() {
        if(viewBinding.createQuizLayout.isVisible){
            viewBinding.createQuizLayout.visibility=View.GONE
        }
        if(quizzes==null){
            quizzes= mutableListOf()
        }
        var item=TeacherQuizItem(null,null,null,null)
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.teacher_course_container
                ,TeacherCourseQuizAddQuestionsFragment(item))
            .commit()
    }



}