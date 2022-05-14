package com.example.lamp.ui.teacher.courses_page.course_content.homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseHomeworkBinding
import com.example.lamp.ui.teacher.courses_page.course_content.homework.homework_recycler_view.TeacherCourseHomeworkAdapter


class TeacherCourseHomeworkFragment:Fragment() {
    //
    lateinit var viewBinding:FragmentTeacherCourseHomeworkBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_teacher_course_homework,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewBinding.homeWorkRecyclerView.adapter=TeacherCourseHomeworkAdapter(mutableListOf("homework1","homework2"))
        viewBinding.addBtn.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(R.id.teacher_course_container,TeacherCourseAddAssignmentFragment())
                .commit()
        }
    }
}