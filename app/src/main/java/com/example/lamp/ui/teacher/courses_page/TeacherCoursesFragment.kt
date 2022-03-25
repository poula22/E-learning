package com.example.lamp.ui.teacher.courses_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCoursesBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.teacher.courses_page.courses_recycler_view.TeacherCoursesAdapter

class TeacherCoursesFragment:Fragment() {
    lateinit var teacherCoursesBinding:FragmentTeacherCoursesBinding
    lateinit var adapter:TeacherCoursesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentTeacherCoursesBinding>(inflater,R.layout.fragment_teacher_courses,container,false).root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        adapter=TeacherCoursesAdapter(TestData.COURSES,1)
        teacherCoursesBinding.teacherCoursesRecyclerView.adapter=adapter
    }

}