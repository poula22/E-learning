package com.example.lamp.ui.student.student_course_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCoursesBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CoursesRVAdapter

class CoursesFragment:Fragment() {
    lateinit var coursesRVAdapter: CoursesRVAdapter
    lateinit var studentCoursesBinding:FragmentStudentCoursesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        studentCoursesBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_student_courses,container,false)
        return studentCoursesBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        coursesRVAdapter=CoursesRVAdapter(TestData.COURSES,type=1)
        studentCoursesBinding.studentCoursesRecyclerView.adapter=coursesRVAdapter
    }
}