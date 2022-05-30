package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseAssignmentBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_course_page.course_content.assignment.AssignmentItem
import com.example.lamp.ui.teacher.courses_page.course_content.assignment.assignment_recycler_view.TeacherCourseAssignmentAdapter


class TeacherCourseAssignmentFragment : Fragment() {
    //
    lateinit var viewBinding: FragmentTeacherCourseAssignmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_course_assignment,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        var dataList = mutableListOf<String>()
        TestData.ASSIGNMENTS.forEach {
            dataList.add(it.title)
        }
        viewBinding.assignmentRecyclerView.adapter = TeacherCourseAssignmentAdapter(dataList)
        val adapter = viewBinding.assignmentRecyclerView.adapter as TeacherCourseAssignmentAdapter
        adapter.onAssignmentClickListener =
            object : TeacherCourseAssignmentAdapter.OnAssignmentClickListener {
                override fun setOnAssignmentClickListener(pos:Int) {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .addToBackStack("")
                        .replace(
                            R.id.teacher_fragment_tab,
                            TeacherAssignmentsFromStudentsFragment(TestData.ASSIGNMENTS.get(pos))
                        )
                        .commit()
                }
            }

        viewBinding.addBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("")
                .replace(
                    R.id.teacher_course_content_container,
                    TeacherCourseAddAssignmentFragment()
                )
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        var toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.isVisible = true
        var drawerLayout: DrawerLayout = requireActivity().findViewById(R.id.drawer_layout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }
}