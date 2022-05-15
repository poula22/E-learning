package com.example.lamp.ui.teacher.courses_page.course_content.material

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CourseItem

class TeacherCourseMaterialFragment(var course: CourseItem?):Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        R.layout.fragment_teacher_course_material
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}