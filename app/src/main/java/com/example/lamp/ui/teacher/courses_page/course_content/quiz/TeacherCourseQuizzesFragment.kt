package com.example.lamp.ui.teacher.courses_page.course_content.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.QuizResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseQuizzesBinding
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.quizzes_recycler_view.TeacherQuizAdapter

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
    }

    private fun subscribeToLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner){
            adapter.changeData(it)
        }
    }

    private fun initViews() {
        viewBinding.createFab.setOnClickListener {
            createQuiz()
        }

        viewBinding.createQuizButton.setOnClickListener {
            createQuiz()
        }
        adapter=TeacherQuizAdapter()
        adapter.onEditQuizListener=object :TeacherQuizAdapter.OnEditQuizListener{
            override fun onEditQuiz(quiz: QuizResponseDTO) {
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
        var item=QuizResponseDTO(null,null,null,null,null,null,null)
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.teacher_course_content_container
                ,TeacherCourseQuizAddQuestionsFragment(item))
            .commit()
    }
    override fun onStart() {
        super.onStart()
        var toolbar: Toolbar =requireActivity().findViewById(R.id.toolbar)
        toolbar.isVisible=true
        var drawerLayout: DrawerLayout =requireActivity().findViewById(R.id.drawer_layout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }



}