package com.example.lamp.ui.teacher.courses_page.course_content.dashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseDashboardBinding
import com.example.lamp.databinding.TestWebViewBinding

class TeacherCourseDashboardFragment : Fragment() {
    lateinit var viewBinding: FragmentTeacherCourseDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_teacher_course_dashboard,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}