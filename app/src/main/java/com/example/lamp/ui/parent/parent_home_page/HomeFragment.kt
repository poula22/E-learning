package com.example.lamp.ui.parent.parent_home_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.parent.parent_children_page.children_recycler_view.ChildrenAdapter
import com.example.lamp.ui.parent.parent_courses_page.course_recycler_view.CourseAdapter

class HomeFragment:Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ChildrenAdapter
    lateinit var coursesRecyclerView: RecyclerView
    lateinit var courseAdapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parent_home,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        recyclerView=requireView().findViewById(R.id.children_recycler_view)
        adapter= ChildrenAdapter(TestData.CHILDREN)
        recyclerView.adapter=adapter
        coursesRecyclerView=requireView().findViewById(R.id.courses_recycler_view)
        courseAdapter= CourseAdapter(TestData.PARENTCOURSES)
        coursesRecyclerView.adapter=courseAdapter
    }
}