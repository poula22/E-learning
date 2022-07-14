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
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCourseQuizzesBinding
import com.example.lamp.ui.student.student_course_page.course_content.quiz.quizzes_recycler_view.StudentQuizAdapter

class StudentQuizzesFragment: Fragment() {
    lateinit var viewBinding: FragmentStudentCourseQuizzesBinding
    lateinit var viewModel: StudentCourseQuizzesViewModel
    var courseId:Int=CONSTANTS.courseId
    val adapter= StudentQuizAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StudentCourseQuizzesViewModel::class.java)
    }
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
        subscribeToLiveData()
        initViews()
        viewModel.getAllQuizzes(courseId)
    }

    private fun subscribeToLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            adapter.changeData(it)
        }
    }

    private fun initViews() {
        viewBinding.createDraftRecycler.adapter = adapter
        adapter.onStartExamListener=object :StudentQuizAdapter.OnStartExamListener{
            override fun onStartExam(quizId:Int) {
                var bundle=Bundle()
                bundle.putInt("courseId",courseId)
                bundle.putInt("quizId",quizId)
                var fragmentSwap= StudentQuizFragment()
                fragmentSwap.arguments=bundle
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.student_course_content_container
                        , fragmentSwap)
                    .addToBackStack("")
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