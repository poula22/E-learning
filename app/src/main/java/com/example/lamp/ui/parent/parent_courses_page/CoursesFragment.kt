package com.example.lamp.ui.parent.parent_courses_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.parent.parent_courses_page.course_recycler_view.CourseAdapter

class CoursesFragment:Fragment() {

    lateinit var coursesRecyclerView: RecyclerView
    lateinit var courseAdapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parent_courses,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        coursesRecyclerView=requireView().findViewById(R.id.parent_student_courses_recycler_view)
        courseAdapter=CourseAdapter(TestData.PARENTCOURSES)
        coursesRecyclerView.adapter=courseAdapter
    }

}