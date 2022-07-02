package com.example.lamp.ui.student.student_course_page

import android.graphics.Bitmap
import android.os.Bundle
import android.text.InputType.TYPE_CLASS_NUMBER
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.data.model.CourseResponse
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCoursesBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_course_page.course_content.StudentCourseDetails
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CoursesRVAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CoursesFragment : Fragment() {
    lateinit var coursesRVAdapter: CoursesRVAdapter
    lateinit var studentCoursesBinding: FragmentStudentCoursesBinding
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
        studentCoursesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_student_courses, container, false)
        return studentCoursesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        //get student id from previous fragment
        initViews()
        viewModel.getAllCourses()
    }

    fun subscribeToLiveData() {
        viewModel.coursesLiveData.observe(viewLifecycleOwner) {
            it?.let {
                coursesRVAdapter.updateCoursesList(it)
            }
        }
        viewModel.course.observe(viewLifecycleOwner) {
            Log.e("taaaaaa", it.toString())
            viewModel.getAllCourses()
        }
    }

    private fun initViews() {
        coursesRVAdapter = CoursesRVAdapter(TestData.COURSES, type = 1)


        coursesRVAdapter.onCourseClickListener = object : CoursesRVAdapter.OnCourseClickListener {
            override fun setOnCourseClickListener(item: CourseResponse?) {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.student_fragment_tab, StudentCourseDetails(item))
                    .commit()
//                viewModel.deleteCourse(item?.id!!)
                    val bottomNavigationView: BottomNavigationView =
                        requireActivity().findViewById(R.id.bottom_navigation_view)
                    bottomNavigationView.isVisible = false
            }
        }
        studentCoursesBinding.studentCoursesRecyclerView.adapter = coursesRVAdapter
        studentCoursesBinding.joinCourseButton.setOnClickListener {
            var courseCode: String? = null
            val join_course = EditText(requireContext())
            join_course.hint = "Enter course code"
            join_course.inputType = TYPE_CLASS_NUMBER
            MaterialAlertDialogBuilder(requireContext())
                // Add customization options here
                .setTitle("Join Course")
                .setView(join_course)
                .setPositiveButton("Join") { dialog, which ->
                    courseCode = join_course.text.toString()
                    if (courseCode.isNullOrEmpty()) {
                        join_course.error = "Please enter course code"
                    } else {
                        viewModel.joinCourse(CONSTANTS.user_id, Integer.parseInt(courseCode))
                    }
                }
                .show()

        }
    }
}