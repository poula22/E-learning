package com.example.lamp.ui.teacher.courses_page.course_content.grades.students_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.domain.model.StudentResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseGradesStudentsBinding
import com.example.lamp.ui.teacher.courses_page.course_content.grades.student_grades_page.TeacherCourseGradesFragment
import com.example.lamp.ui.teacher.courses_page.course_content.grades.students_page.students_rv.TeacherStudentsOverallGradesAdapter

class TeacherCourseStudentsFragment(var studentsList: MutableList<StudentResponseDTO>? = null) :
    Fragment() {

    lateinit var viewBinding: FragmentTeacherCourseGradesStudentsBinding
    lateinit var viewModel: TeacherCourseStudentsViewModel
    lateinit var teacherStudentsOverallGradesAdapter: TeacherStudentsOverallGradesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeacherCourseStudentsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_course_grades_students,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initViews()
        viewModel.getStudentsByCourseId(CONSTANTS.courseId)
    }

    fun subscribeToLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            teacherStudentsOverallGradesAdapter.changeData(it)
        }
    }

    private fun initViews() {

        teacherStudentsOverallGradesAdapter = TeacherStudentsOverallGradesAdapter(studentsList)
        viewBinding.studentsRv.adapter = teacherStudentsOverallGradesAdapter


        val adapter = viewBinding.studentsRv.adapter as TeacherStudentsOverallGradesAdapter
        adapter.onStudentClickListener =
            object : TeacherStudentsOverallGradesAdapter.OnStudentClickListener {
                override fun setOnStudentClickListener(student: StudentResponseDTO) {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .addToBackStack("")
                        .replace(
                            R.id.teacher_course_content_container,
                            TeacherCourseGradesFragment(student)
                        )
                        .commit()
                }
            }


    }


}