package com.example.lamp.ui.teacher.home_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherHomeBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CourseItem
import com.example.lamp.ui.teacher.courses_page.course_content.TeacherCourseDetails
import com.example.lamp.ui.teacher.courses_page.courses_recycler_view.TeacherCoursesAdapter
import com.example.lamp.ui.teacher.profile_page.TeacherProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class TeacherHomeFragment : Fragment() {
    lateinit var viewBinding: FragmentTeacherHomeBinding
    lateinit var adapter: TeacherCoursesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate<FragmentTeacherHomeBinding>(
            inflater,
            R.layout.fragment_teacher_home,
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
        adapter = TeacherCoursesAdapter(TestData.COURSES, 0)
        adapter.onCourseClickListener = object : TeacherCoursesAdapter.OnCourseClickListener {
            override fun setOnCourseClickListener(item: CourseItem?) {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.teacher_fragment_tab, TeacherCourseDetails(item))
                    .commit()
                val bottomNavigationView: BottomNavigationView =
                    requireActivity().findViewById(R.id.bottom_navigation_view)
                bottomNavigationView.isVisible = false
            }

        }
        viewBinding.coursesRecyclerView.adapter = adapter

        viewBinding.roundedProfile.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .addToBackStack("")
                .replace(R.id.teacher_fragment_tab, TeacherProfileFragment())
                .commit()
        }

    }

}