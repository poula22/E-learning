package com.example.lamp.ui.parent.parent_home_page.courses_page

import CoursesViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.ParentChildCoursesResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentParentCoursesBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.parent.parent_home_page.courses_page.course_recycler_view.CourseAdapter
import com.example.lamp.ui.parent.parent_home_page.courses_page.parent_grades.StudentCourseGradesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class CoursesFragment : Fragment() {
    lateinit var fragmentParentCoursesBinding: FragmentParentCoursesBinding
    lateinit var courseAdapter: CourseAdapter
    lateinit var viewModel: CoursesViewModel
    var studentId:Int?=null

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
        studentId=requireArguments().getInt("student_id")
        viewModel.getCoursesByStudentId(studentId!!) //test data
    }

    private fun subscribeToLiveData() {
        viewModel.courseLiveData.observe(viewLifecycleOwner) {
            courseAdapter.changeData(it)
        }
    }

    private fun initViews() {
        courseAdapter = CourseAdapter()
        fragmentParentCoursesBinding.parentCoursesRecyclerView.adapter = courseAdapter

        courseAdapter.onCourseClickListener =
            object : CourseAdapter.OnCourseClickListener {
                override fun setOnCourseClickListener(course: ParentChildCoursesResponseDTO) {
                    val bundle=Bundle()
                    bundle.putInt("courseId",course.id!!)
                    bundle.putInt("studentId",studentId!!)
                    val fragment=StudentCourseGradesFragment()
                    fragment.arguments=bundle
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .addToBackStack("")
                        .replace(
                            R.id.parent_fragment_tab,
                            fragment
                        )
                        .commit()
                }
            }
    }
    override fun onResume() {
        super.onResume()
        val bottomNavigationView: BottomNavigationView =
            requireActivity().findViewById(R.id.parent_bottom_naviagation_view)
        bottomNavigationView.isVisible = false
    }

    override fun onDetach() {
        super.onDetach()
        val bottomNavigationView: BottomNavigationView =
            requireActivity().findViewById(R.id.parent_bottom_naviagation_view)
        bottomNavigationView.isVisible = true
    }

}