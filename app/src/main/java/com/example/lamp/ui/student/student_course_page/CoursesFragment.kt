package com.example.lamp.ui.student.student_course_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CoursesRVAdapter

class CoursesFragment:Fragment() {
    lateinit var coursesRecyclerView: RecyclerView
    lateinit var coursesRVAdapter: CoursesRVAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_courses,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        coursesRecyclerView=requireView().findViewById(R.id.student_courses_recycler_view)
        coursesRVAdapter=CoursesRVAdapter(TestData.COURSES,type=1)
        coursesRecyclerView.adapter=coursesRVAdapter
    }
}