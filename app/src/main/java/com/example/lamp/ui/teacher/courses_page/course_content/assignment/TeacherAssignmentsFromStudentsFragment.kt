package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherAssignmentsFromStudentsBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_course_page.course_content.assignment.AssignmentItem
import com.example.lamp.ui.teacher.courses_page.course_content.assignment.assignments_from_students.TeacherAssignmentsFromStudentsAdapter

class TeacherAssignmentsFromStudentsFragment(val item : AssignmentItem?) : Fragment() {

    lateinit var viewBinding: FragmentTeacherAssignmentsFromStudentsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_assignments_from_students,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val adapter = TeacherAssignmentsFromStudentsAdapter(TestData.ASSIGNMENT_FROM_STUDENT)
        viewBinding.assignmentsFromStudentsRv.adapter= adapter



    }


}