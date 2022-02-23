package com.example.lamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.lamp.ui.parent.ParentContainerFragment
import com.example.lamp.ui.student.StudentContainerFragment
import com.example.lamp.ui.sign_up_page.SignUpFragment
import com.example.lamp.ui.sign_in_page.SigninFragment
import com.example.lamp.ui.teacher.TeacherContainerFragment
import com.example.lamp.ui.teacher.courses_page.courses_bottom_sheet.TeacherAddCoursesBottomSheet
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pushFragment(SigninFragment())

    }
    fun signUp(view:View){
        pushFragment(SignUpFragment())
    }
    fun signIn(view:View){
        pushFragment(ParentContainerFragment())
    }
    fun pushFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack(null)
            .commit()
    }

}