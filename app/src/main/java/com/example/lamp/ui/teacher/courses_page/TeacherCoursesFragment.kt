package com.example.lamp.ui.teacher.courses_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.CourseResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCoursesBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.teacher.courses_page.course_content.TeacherCourseDetails
import com.example.lamp.ui.teacher.courses_page.courses_bottom_sheet.TeacherAddCoursesBottomSheet
import com.example.lamp.ui.teacher.courses_page.courses_recycler_view.TeacherCoursesAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TeacherCoursesFragment : Fragment() {
    lateinit var teacherCoursesBinding: FragmentTeacherCoursesBinding
    lateinit var adapter: TeacherCoursesAdapter
    lateinit var viewModel: TeacherCoursesViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        teacherCoursesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_teacher_courses, container, false)
        return teacherCoursesBinding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(TeacherCoursesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initViews()
        viewModel.getAllCourses()
    }


    fun subscribeToLiveData() {
        viewModel.coursesLiveData.observe(viewLifecycleOwner){
            adapter.changeData(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllCourses()
    }


    private fun initViews() {
        adapter = TeacherCoursesAdapter(TestData.COURSES, 1)
        adapter.onCourseClickListener=object :TeacherCoursesAdapter.OnCourseClickListener{
            override fun setOnCourseClickListener(item: CourseResponseDTO?) {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.teacher_fragment_tab,TeacherCourseDetails(item))
                    .commit()
                val floatingActionBtn: FloatingActionButton =requireActivity().findViewById(R.id.floating_action_btn)
                floatingActionBtn.isVisible=false
                val bottomNavigationView: BottomNavigationView =
                    requireActivity().findViewById(R.id.bottom_navigation_view)
                bottomNavigationView.isVisible = false
            }

        }
        teacherCoursesBinding.teacherCoursesRecyclerView.adapter = adapter

        teacherCoursesBinding.floatingActionBtn.setOnClickListener {
            showAddBottomSheet()
        }
    }

    private fun showAddBottomSheet() {
        val addCourseBottomSheet = TeacherAddCoursesBottomSheet()
        addCourseBottomSheet.show(requireActivity().supportFragmentManager, "")
        addCourseBottomSheet.onCourseAddedListener=object :TeacherAddCoursesBottomSheet.OnCourseAddedListener{
            override fun OnAddCourse() {
                viewModel.getAllCourses()
            }

        }
    }

}