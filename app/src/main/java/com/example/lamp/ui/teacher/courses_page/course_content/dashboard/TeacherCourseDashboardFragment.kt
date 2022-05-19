package com.example.lamp.ui.teacher.courses_page.course_content.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseDashboardBinding
import com.example.lamp.ui.teacher.courses_page.TeacherCoursesFragment
import com.example.lamp.ui.teacher.students_page.TeacherStudentsFragment

class TeacherCourseDashboardFragment : Fragment() {
    lateinit var viewBinding: FragmentTeacherCourseDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_course_dashboard,
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
        viewBinding.coursesNumberCard.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction().replace(R.id.fragment_container, TeacherCoursesFragment())
                .addToBackStack("")
                .commit()
        }

        viewBinding.studentsNumberCard.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction().replace(R.id.fragment_container, TeacherStudentsFragment())
                .addToBackStack("")
                .commit()
        }
    }
}