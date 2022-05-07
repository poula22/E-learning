package com.example.lamp.ui.teacher.students_page.students_recycler_view

import com.google.android.material.button.MaterialButton

data class StudentItem(
    val studentName: String,
    val studentImageId: Int,
    val phone: String,
    val email: String,
    val facebookAcc: String
)
