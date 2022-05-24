package com.example.lamp.ui.student.student_course_page.course_content

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCourseDetailsBinding
import com.example.lamp.ui.student.student_course_page.course_content.assignment.StudentCourseAssignmentFragment
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CourseItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class StudentCourseDetails(var course: CourseItem?) : Fragment() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewBinding: FragmentStudentCourseDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_student_course_details, container, false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        (requireActivity() as AppCompatActivity?)!!
            .setSupportActionBar(viewBinding.studentCourseContainer.toolbar)
        val drawerLayout: DrawerLayout = viewBinding.drawerLayout
        val navView: NavigationView = viewBinding.navView
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        viewBinding.studentCourseContainer.toolbar.title = course?.courseName

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dashboard,
                R.id.announcements,
                R.id.assignment,
                R.id.grades,
                R.id.material,
                R.id.quizzes,
            ), drawerLayout
        )
        viewBinding.studentCourseContainer.toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }
        viewBinding.navView.setNavigationItemSelectedListener { item ->
            viewBinding.studentCourseContainer.toolbar.subtitle =
                viewBinding.navView.menu.findItem(item.itemId).title
            if (item.itemId == R.id.assignment) {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.student_course_content_container,
                        StudentCourseAssignmentFragment()
                    )
                    .commit()
            } else if (item.itemId == R.id.material) {


            } else if (item.itemId == R.id.dashboard) {

            } else if (item.itemId == R.id.quizzes) {

            }
            drawerLayout.close()
            viewBinding.studentCourseContainer.infoIcon.setOnClickListener {

            }
            return@setNavigationItemSelectedListener true
        }
        viewBinding.navView.setCheckedItem(R.id.dashboard)
        viewBinding.navView.menu.performIdentifierAction(R.id.dashboard, 0)


        viewBinding.studentCourseContainer.infoIcon.setOnClickListener {
            viewBinding.studentCourseContainer.toolbar.subtitle = "Course Info"
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
