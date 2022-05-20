package com.example.lamp.ui.teacher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherContainerAllTabsBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.teacher.courses_page.TeacherCoursesFragment
import com.example.lamp.ui.teacher.courses_page.course_content.material.TeacherCourseAddSectionFragment
import com.example.lamp.ui.teacher.courses_page.courses_bottom_sheet.TeacherAddCoursesBottomSheet
import com.example.lamp.ui.teacher.home_page.TeacherHomeFragment
import com.example.lamp.ui.teacher.profile_page.TeacherProfileFragment
import com.example.lamp.ui.teacher.students_page.TeacherStudentsFragment
import com.example.lamp.ui.teacher.tools_page.TeacherToolsFragment

class TeacherContainerFragment : Fragment() {
    lateinit var teacherContainerAllTabsBinding: FragmentTeacherContainerAllTabsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        teacherContainerAllTabsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_container_all_tabs,
            container,
            false
        )
        return teacherContainerAllTabsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        teacherContainerAllTabsBinding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.profile) {
                teacherContainerAllTabsBinding.floatingActionBtn.isVisible = false
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(
                        teacherContainerAllTabsBinding.teacherFragmentTab.id,
                        TeacherProfileFragment()
                    )
                    .commit()
            } else if (menuItem.itemId == R.id.courses) {
                teacherContainerAllTabsBinding.floatingActionBtn.isVisible = true
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(
                        teacherContainerAllTabsBinding.teacherFragmentTab.id,
                        TeacherCoursesFragment()
                    )
                    .commit()
            } else if (menuItem.itemId == R.id.students) {
                teacherContainerAllTabsBinding.floatingActionBtn.isVisible = false
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(
                        teacherContainerAllTabsBinding.teacherFragmentTab.id,
                        TeacherStudentsFragment(TestData.STUDENTS)
                    )
                    .commit()
            } else if (menuItem.itemId == R.id.tools) {
                teacherContainerAllTabsBinding.floatingActionBtn.isVisible = false
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(
                        teacherContainerAllTabsBinding.teacherFragmentTab.id,
                        TeacherToolsFragment()
                    )
                    .commit()
            } else if (menuItem.itemId == R.id.home) {
                teacherContainerAllTabsBinding.floatingActionBtn.isVisible = false
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(
                        teacherContainerAllTabsBinding.teacherFragmentTab.id,
                        TeacherHomeFragment()
                    )
                    .commit()
            }
            return@setOnItemSelectedListener true
        }
        teacherContainerAllTabsBinding.bottomNavigationView.selectedItemId = R.id.home
        teacherContainerAllTabsBinding.floatingActionBtn.setOnClickListener {
            showAddBottomSheet()
        }
    }

    private fun showAddBottomSheet() {
        val addCourseBottomSheet = TeacherAddCoursesBottomSheet()
        addCourseBottomSheet.show(requireActivity().supportFragmentManager, "")
    }
}