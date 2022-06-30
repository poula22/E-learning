package com.example.lamp.ui.teacher.courses_page.course_content

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.commonFunctions.CONSTANTS
import com.example.data.model.CourseResponse
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseDetailsBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.teacher.courses_page.course_content.assignment.TeacherCourseAssignmentFragment
import com.example.lamp.ui.teacher.courses_page.course_content.grades.students_page.TeacherCourseStudentsFragment
import com.example.lamp.ui.teacher.courses_page.course_content.material.TeacherCourseMaterialFragment
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.TeacherCourseQuizzesFragment
import com.example.lamp.ui.teacher.courses_page.course_content.settings.TeacherCourseSettingsFragment
import com.example.lamp.ui.teacher.students_page.TeacherStudentsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class TeacherCourseDetails(var course: CourseResponse?) : Fragment() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewBinding: FragmentTeacherCourseDetailsBinding
    var fragment: Fragment? = null
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
        CONSTANTS.courseId = course?.id!!
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
                    fragment = TeacherCourseAssignmentFragment()
                }
                R.id.material -> {
                    fragment = TeacherCourseMaterialFragment(null)
                }
                R.id.edit_course -> {
                    val bundle=Bundle()
                    bundle.putSerializable("course",course)
                    fragment = TeacherCourseSettingsFragment()
                    fragment?.arguments=bundle
                }
                R.id.students -> {
                    fragment = TeacherStudentsFragment(TestData.STUDENTS)
                }
                R.id.quizzes -> {
                    fragment = TeacherCourseQuizzesFragment()
                }
                R.id.grades -> {
                    fragment = TeacherCourseStudentsFragment()
                }
            }


            viewBinding.teacherCourseContainer.settingsIcon.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.teacher_course_content_container, TeacherCourseSettingsFragment())
                    .addToBackStack(null)
                    .commit()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerOpened(drawerView: View) {
            }

            override fun onDrawerClosed(drawerView: View) {
                fragment?.let {
                    Handler(Looper.getMainLooper()).postDelayed({
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.teacher_course_content_container,
                                it
                            )
                            .commit()
                    }, 0)

                }
                fragment = null

            }

            override fun onDrawerStateChanged(newState: Int) {

            }

        })
        viewBinding.navView.setCheckedItem(R.id.announcements)
        viewBinding.navView.menu.performIdentifierAction(R.id.announcements, 0)
        viewBinding.teacherCourseContainer.settingsIcon.setOnClickListener {
            viewBinding.teacherCourseContainer.toolbar.subtitle = "Settings"
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.teacher_course_content_container, TeacherCourseSettingsFragment())
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        viewBinding.navView.menu.performIdentifierAction(
            viewBinding.navView.checkedItem?.itemId!!,
            0
        )
        fragment?.let {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.teacher_course_content_container,
                    fragment!!
                )
                .commit()
        }
        fragment = null
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