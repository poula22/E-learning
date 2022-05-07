package com.example.lamp.ui.teacher.students_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherStudentsBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.teacher.students_page.students_recycler_view.TeacherStudentsAdapter

class TeacherStudentsFragment : Fragment() {
    lateinit var teacherStudentsBinding: FragmentTeacherStudentsBinding
    lateinit var adapter: TeacherStudentsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        teacherStudentsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_teacher_students, container, false)
        return teacherStudentsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() {
        adapter = TeacherStudentsAdapter(TestData.STUDENTS)
        teacherStudentsBinding.teacherStudentsRecyclerView.adapter = adapter

    }

}