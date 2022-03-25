package com.example.lamp.ui.parent.parent_home_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.FragmentParentHomeBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.parent.parent_children_page.children_recycler_view.ChildrenAdapter
import com.example.lamp.ui.parent.parent_courses_page.course_recycler_view.CourseAdapter

class HomeFragment:Fragment() {
    lateinit var fragmentParentHomeBinding: FragmentParentHomeBinding
    lateinit var adapter: ChildrenAdapter
    lateinit var courseAdapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentParentHomeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_parent_home,container,false)
        return fragmentParentHomeBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        adapter= ChildrenAdapter(TestData.CHILDREN)
        fragmentParentHomeBinding.childrenRecyclerView.adapter=adapter
        courseAdapter= CourseAdapter(TestData.PARENTCOURSES)
        fragmentParentHomeBinding.coursesRecyclerView.adapter=courseAdapter
    }
}