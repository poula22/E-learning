package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseAssignmentBinding
import com.example.lamp.ui.teacher.courses_page.course_content.assignment.assignment_recycler_view.TeacherCourseAssignmentAdapter


class TeacherCourseAssignmentFragment:Fragment() {
    //
    lateinit var viewBinding:FragmentTeacherCourseAssignmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_teacher_course_assignment,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewBinding.assignmentRecyclerView.adapter=TeacherCourseAssignmentAdapter(mutableListOf("assignment1","assignment2"))
        viewBinding.addBtn.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("")
                .replace(this.id,TeacherCourseAddAssignmentFragment())
                .commit()
        }
    }
}