package com.example.lamp.ui.teacher.courses_page.course_content

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseDetailsBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CourseItem
import com.example.lamp.ui.teacher.courses_page.course_content.assignment.TeacherCourseAssignmentFragment
import com.example.lamp.ui.teacher.courses_page.course_content.dashboard.todo_list.TeacherCourseDashboardFragment
import com.example.lamp.ui.teacher.courses_page.course_content.material.TeacherCourseMaterialFragment
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.TeacherCourseQuizzesFragment
import com.example.lamp.ui.teacher.courses_page.course_content.settings.TeacherCourseSettingsFragment
import com.example.lamp.ui.teacher.students_page.TeacherStudentsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class TeacherCourseDetails(var course: CourseItem?) : Fragment() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewBinding: FragmentTeacherCourseDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_course_details, container, false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        (requireActivity() as AppCompatActivity?)!!
            .setSupportActionBar(viewBinding.teacherCourseContainer.toolbar)
        val drawerLayout: DrawerLayout = viewBinding.drawerLayout
        val navView: NavigationView = viewBinding.navView
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        viewBinding.teacherCourseContainer.toolbar.title = course?.courseName

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dashboard,
                R.id.announcements,
                R.id.assignment,
                R.id.grades,
                R.id.students,
                R.id.material,
                R.id.quizzes,
                R.id.edit_course
            ), drawerLayout
        )
        viewBinding.teacherCourseContainer.toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }
        viewBinding.navView.setNavigationItemSelectedListener { item ->
            viewBinding.teacherCourseContainer.toolbar.subtitle =
                viewBinding.navView.menu.findItem(item.itemId).title
            when (item.itemId) {
                R.id.assignment -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.course_content_container, TeacherCourseAssignmentFragment())
                        .commit()
                }
                R.id.material -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.course_content_container, TeacherCourseMaterialFragment(
                                TestData.COURSES[0]
                            )
                        )
                        .commit()
                }
                R.id.edit_course -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.course_content_container, TeacherCourseSettingsFragment())
                        .commit()
                }
                R.id.students -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.course_content_container,
                            TeacherStudentsFragment(TestData.STUDENTS)
                        )
                        .commit()
                }
                R.id.dashboard -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.course_content_container, TeacherCourseDashboardFragment())
                        .commit()
                }
                R.id.quizzes -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.course_content_container, TeacherCourseQuizzesFragment())
                        .commit()
                }
            }
            drawerLayout.close()
            viewBinding.teacherCourseContainer.settingsIcon.setOnClickListener {

            }
            return@setNavigationItemSelectedListener true
        }
        viewBinding.navView.setCheckedItem(R.id.dashboard)
        viewBinding.navView.menu.performIdentifierAction(R.id.dashboard, 0)


        viewBinding.teacherCourseContainer.settingsIcon.setOnClickListener {
            viewBinding.teacherCourseContainer.toolbar.subtitle = "Settings"
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.course_content_container, TeacherCourseSettingsFragment())
                .commit()
        }
    }

    override fun onDetach() {
        super.onDetach()
        val bottomNavigationView: BottomNavigationView =
            requireActivity().findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.isVisible = true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.teacher_course_details, menu)
    }

}