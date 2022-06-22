package com.example.lamp.ui.teacher.courses_page.courses_bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherAddCourseBinding
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CourseItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class TeacherAddCoursesBottomSheet : BottomSheetDialogFragment() {
    lateinit var teacherAddCourseBinding: FragmentTeacherAddCourseBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        teacherAddCourseBinding = DataBindingUtil.inflate<FragmentTeacherAddCourseBinding>(
            inflater,
            R.layout.fragment_teacher_add_course,
            container,
            false
        )
        return teacherAddCourseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews() {
        teacherAddCourseBinding.addCourseBtn.setOnClickListener {
            if (validateForm()) {
                val courseName = teacherAddCourseBinding.courseNameLayout.editText?.text.toString()
                val courseCode = teacherAddCourseBinding.codeLayout.editText?.text.toString()
                val description =
                    teacherAddCourseBinding.descriptionLayout.editText?.text.toString()
                val courseImageId = R.drawable.login
                val startDate = "2020-01-01"
                val endDate = "2020-01-01"
                val course =
                    CourseItem(
                        courseName,
                        null,
                        courseCode,
                        description,
                        courseImageId,
                        startDate,
                        endDate
                    )
                dismiss()
            }
        }
    }

    fun validateForm(): Boolean {
        var isValid = true
        if (teacherAddCourseBinding.courseNameLayout.editText?.text.toString().isBlank()) {
            teacherAddCourseBinding.courseNameLayout.error = "please enter todo details"
            isValid = false
        } else {
            teacherAddCourseBinding.courseNameLayout.error = null
        }
        if (teacherAddCourseBinding.codeLayout.editText?.text.toString().isBlank()) {
            teacherAddCourseBinding.codeLayout.error = "please enter todo details"
            isValid = false
        } else {
            teacherAddCourseBinding.codeLayout.error = null
        }
        if (teacherAddCourseBinding.descriptionLayout.editText?.text.toString().isBlank()) {
            teacherAddCourseBinding.descriptionLayout.error = "please enter todo details"
            isValid = false
        } else {
            teacherAddCourseBinding.descriptionLayout.error = null
        }
        return isValid
    }

}