package com.example.lamp.ui.teacher.courses_page.course_content.homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseHomeworkBinding


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
}