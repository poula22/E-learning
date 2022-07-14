 package com.example.lamp.ui.teacher.courses_page.course_content.quiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.domain.model.QuizResponseDTO
import com.example.domain.model.TeacherQuizResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseQuizzesBinding
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.quizzes_recycler_view.TeacherQuizAdapter
import com.google.type.DateTime
import java.text.SimpleDateFormat
import java.util.*

class TeacherCourseQuizzesFragment: Fragment() {

    lateinit var viewBinding: FragmentTeacherCourseQuizzesBinding
    lateinit var viewModel: TeacherCourseQuizzesViewModel
    lateinit var adapter: TeacherQuizAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeacherCourseQuizzesViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_course_quizzes,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initViews()
        viewModel.getAllQuizzes()
    }

    private fun subscribeToLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner){
            adapter.changeData(it)
        }
        viewModel.deleteLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "quiz deleted successfully", Toast.LENGTH_SHORT).show()
            viewModel.getAllQuizzes()
        }

    }

    private fun initViews() {
        viewBinding.createFab.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .addToBackStack("")
                .replace(R.id.teacher_course_content_container
                    ,TeacherCourseQuizAddQuestionsFragment())
                .commit()
        }

        viewBinding.createQuizButton.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .addToBackStack("")
                .replace(R.id.teacher_course_content_container
                    ,TeacherCourseQuizAddQuestionsFragment())
                .commit()
        }
        adapter=TeacherQuizAdapter()

        ////////////////////////////////////////////////
        // don't forget to make new quiz edit fragment//
        ////////////////////////////////////////////////

        adapter.onEditQuizListener=object :TeacherQuizAdapter.OnEditQuizListener{
            override fun onEditQuiz(quiz: TeacherQuizResponseDTO) {
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.teacher_course_container
                    ,TeacherCourseQuizAddQuestionsFragment())
                    .commit()
            }

            override fun onDeleteQuiz(quiz: TeacherQuizResponseDTO) {
                viewModel.deleteQuiz(quiz)
            }

        }
        viewBinding.createDraftRecycler.adapter=adapter

    }


    override fun onStart() {
        super.onStart()
        var toolbar: Toolbar =requireActivity().findViewById(R.id.toolbar)
        toolbar.isVisible=true
        var drawerLayout: DrawerLayout =requireActivity().findViewById(R.id.drawer_layout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }



}