package com.example.lamp.ui.parent.parent_courses_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.FragmentParentCoursesBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.parent.parent_courses_page.course_recycler_view.CourseAdapter

class CoursesFragment:Fragment() {
    lateinit var fragmentParentCoursesBinding: FragmentParentCoursesBinding
    lateinit var courseAdapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentParentCoursesBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_parent_courses,container,false)
        return fragmentParentCoursesBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        courseAdapter=CourseAdapter(TestData.PARENTCOURSES)
        fragmentParentCoursesBinding.parentCoursesRecyclerView.adapter=courseAdapter
    }

}