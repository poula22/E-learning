package com.example.lamp.ui.teacher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherContainerAllTabsBinding
import com.example.lamp.ui.student.student_course_page.CoursesFragment
import com.example.lamp.ui.student.student_features_page.FeaturesFragment
import com.example.lamp.ui.student.student_home_page.HomeFragment
import com.example.lamp.ui.teacher.courses_page.TeacherCoursesFragment
import com.example.lamp.ui.teacher.courses_page.courses_bottom_sheet.TeacherAddCoursesBottomSheet
import com.example.lamp.ui.teacher.home_page.TeacherHomeFragment
import com.example.lamp.ui.teacher.profile_page.TeacherProfileFragment
import com.example.lamp.ui.teacher.students_page.TeacherStudentsFragment
import com.example.lamp.ui.teacher.tools_page.TeacherToolsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TeacherContainerFragment:Fragment() {
    lateinit var teacherContainerAllTabsBinding:FragmentTeacherContainerAllTabsBinding
//    lateinit var bottomNavigationView: BottomNavigationView
//    lateinit var teacherContainerAllTabsBinding.floatingActionBtn: FloatingActionButton
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teacher_container_all_tabs,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        teacherContainerAllTabsBinding.bottomNavigationView.setOnItemSelectedListener {    menuItem->
            if(menuItem.itemId== R.id.profile){
                teacherContainerAllTabsBinding.floatingActionBtn.isVisible=false
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.teacher_fragment_tab, TeacherProfileFragment())
                    .commit()
            }else if(menuItem.itemId== R.id.courses){
                teacherContainerAllTabsBinding.floatingActionBtn.isVisible=true
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.teacher_fragment_tab, TeacherCoursesFragment())
                    .commit()
            }else if(menuItem.itemId== R.id.students){
                teacherContainerAllTabsBinding.floatingActionBtn.isVisible=false
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.teacher_fragment_tab, TeacherStudentsFragment())
                    .commit()
            }else if(menuItem.itemId== R.id.tools){
                teacherContainerAllTabsBinding.floatingActionBtn.isVisible=false
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.teacher_fragment_tab, TeacherToolsFragment())
                    .commit()
            }else if(menuItem.itemId== R.id.home){
                teacherContainerAllTabsBinding.floatingActionBtn.isVisible=false
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.teacher_fragment_tab, TeacherHomeFragment())
                    .commit()
            }
            return@setOnItemSelectedListener true
        }
        teacherContainerAllTabsBinding.bottomNavigationView.selectedItemId= R.id.home
        teacherContainerAllTabsBinding.floatingActionBtn.setOnClickListener{
            showAddBottomSheet()
        }
    }
    private fun showAddBottomSheet() {
        val addCourseBottomSheet= TeacherAddCoursesBottomSheet()
        addCourseBottomSheet.show(requireActivity().supportFragmentManager,"")
    }
}