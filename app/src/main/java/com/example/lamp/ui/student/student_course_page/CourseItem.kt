package com.example.lamp.ui.student.student_course_page

import android.graphics.Bitmap
import com.example.domain.model.CourseResponseDTO

data class CourseItem (val courseResponseDTO: CourseResponseDTO, var bitmap: Bitmap?=null)