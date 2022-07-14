package com.example.lamp.ui.parent.parent_home_page.courses_page

import CoursesViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.ParentChildCoursesResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentParentCoursesBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.parent.parent_home_page.courses_page.course_recycler_view.CourseAdapter
import com.example.lamp.ui.parent.parent_home_page.courses_page.parent_grades.StudentCourseGradesFragment

class CoursesFragment(childId: Int) : Fragment() {
    lateinit var fragmentParentCoursesBinding: FragmentParentCoursesBinding
    lateinit var courseAdapter: CourseAdapter

    lateinit var viewModel: CoursesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoursesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentParentCoursesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_parent_courses, container, false)
        return fragmentParentCoursesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initViews()
        viewModel.getCoursesByStudentId(1) //test data
    }

    private fun subscribeToLiveData() {
        viewModel.courseLiveData.observe(viewLifecycleOwner) {
            courseAdapter.changeData(it)
        }
    }

    private fun initViews() {
        courseAdapter = CourseAdapter(TestData.PARENTCOURSES)
        fragmentParentCoursesBinding.parentCoursesRecyclerView.adapter = courseAdapter


        courseAdapter.onCourseClickListener =
            object : CourseAdapter.OnCourseClickListener {
                override fun setOnCourseClickListener(course: ParentChildCoursesResponseDTO) {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .addToBackStack("")
                        .replace(
                            R.id.teacher_fragment_tab,
                            StudentCourseGradesFragment(course.id!!)
                        )
                        .commit()
                }
            }


    }

}