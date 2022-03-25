package com.example.lamp.ui.teacher.home_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherHomeBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.teacher.courses_page.courses_recycler_view.TeacherCoursesAdapter

class TeacherHomeFragment:Fragment() {
    lateinit var teacherHomeBinding: FragmentTeacherHomeBinding
    lateinit var adapter: TeacherCoursesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        teacherHomeBinding = DataBindingUtil.inflate<FragmentTeacherHomeBinding>(inflater,R.layout.fragment_teacher_home,container,false)
        return teacherHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        adapter= TeacherCoursesAdapter(TestData.COURSES,0)
        teacherHomeBinding.coursesRecyclerView.adapter=adapter
    }

}