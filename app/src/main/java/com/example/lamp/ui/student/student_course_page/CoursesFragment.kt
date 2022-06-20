package com.example.lamp.ui.student.student_course_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCoursesBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_course_page.course_content.StudentCourseDetails
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CourseItem
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CoursesRVAdapter
import com.example.lamp.ui.teacher.courses_page.course_content.TeacherCourseDetails
import com.example.lamp.ui.teacher.courses_page.courses_recycler_view.TeacherCoursesAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CoursesFragment:Fragment() {
    lateinit var coursesRVAdapter: CoursesRVAdapter
    lateinit var studentCoursesBinding:FragmentStudentCoursesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        studentCoursesBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_student_courses,container,false)
        return studentCoursesBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        coursesRVAdapter=CoursesRVAdapter(TestData.COURSES,type=1)


        coursesRVAdapter.onCourseClickListener = object : CoursesRVAdapter.OnCourseClickListener{
            override fun setOnCourseClickListener(item: CourseItem?) {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.student_fragment_tab, StudentCourseDetails(item))
                    .commit()

                val bottomNavigationView: BottomNavigationView =
                    requireActivity().findViewById(R.id.bottom_navigation_view)
                bottomNavigationView.isVisible = false
            }
        }
        studentCoursesBinding.studentCoursesRecyclerView.adapter=coursesRVAdapter
        studentCoursesBinding.joinCourseButton.setOnClickListener {

        }
    }
}