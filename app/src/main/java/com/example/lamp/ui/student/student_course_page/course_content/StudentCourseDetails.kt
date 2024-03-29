package com.example.lamp.ui.student.student_course_page.course_content

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import com.example.common_functions.CONSTANTS
import com.example.domain.model.CourseResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCourseDetailsBinding
import com.example.lamp.ui.student.student_course_page.course_content.assignment.StudentCourseAssignmentFragment
import com.example.lamp.ui.student.student_course_page.course_content.grades.StudentCourseGradesFragment
import com.example.lamp.ui.student.student_course_page.course_content.material.StudentCourseMaterialFragment
import com.example.lamp.ui.student.student_course_page.course_content.quiz.StudentQuizzesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import retrofit2.Response


class StudentCourseDetails(var course: CourseResponseDTO?) : Fragment() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewBinding: FragmentStudentCourseDetailsBinding
    private var fragment: Fragment? = null
    private lateinit var viewModel: StudentCourseDetailsViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(StudentCourseDetailsViewModel::class.java)
    }

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
        CONSTANTS.courseId = course?.id!!
        subscribeToLiveData()
        initViews()

    }

    private fun subscribeToLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            isCourseDropped(it)
        }
    }

    private fun isCourseDropped(response: Response<Void>?) {
        if (response?.code() == 200) {
            Toast.makeText(requireContext(), "Course Dropped", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()
        }
        else{
            Toast.makeText(requireContext(), "Course Not Dropped", Toast.LENGTH_SHORT).show()
        }
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
            navigateToSelectedFragment(item,drawerLayout)
            return@setNavigationItemSelectedListener true
        }
        viewBinding.studentCourseContainer.infoIcon.setOnClickListener {

        }
        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerOpened(drawerView: View) {
            }

            override fun onDrawerClosed(drawerView: View) {
                doNavigation()
            }

            override fun onDrawerStateChanged(newState: Int) {
            }

        })
        viewBinding.navView.setCheckedItem(R.id.dashboard)
        viewBinding.navView.menu.performIdentifierAction(R.id.dashboard, 0)


        viewBinding.studentCourseContainer.infoIcon.setOnClickListener {
            viewBinding.studentCourseContainer.toolbar.subtitle = "Course Info"
        }

        viewBinding.studentCourseContainer.infoIcon.setOnClickListener {
            dropCourse()
        }
    }

    private fun dropCourse() {
        viewModel.dropCourse()
    }

    private fun doNavigation() {
        fragment?.let {
            Handler(Looper.getMainLooper()).postDelayed({
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.student_course_content_container,
                        it
                    )
                    .commit()
            }, 0)

        }
        fragment = null
    }

    private fun navigateToSelectedFragment(item: MenuItem,drawerLayout: DrawerLayout) {
        viewBinding.studentCourseContainer.toolbar.subtitle =
            viewBinding.navView.menu.findItem(item.itemId).title
        if (item.itemId == R.id.assignment) {
//                var bundle=Bundle()
//                bundle.putInt("courseId",course?.id!!)
            var fragmentSwap = StudentCourseAssignmentFragment()
//                fragmentSwap.arguments=bundle
            fragment = fragmentSwap
        } else if (item.itemId == R.id.material) {
            var fragmentSwap = StudentCourseMaterialFragment()
            fragment = fragmentSwap
        } else if (item.itemId == R.id.dashboard) {

        } else if (item.itemId == R.id.quizzes) {
            var fragmentSwap = StudentQuizzesFragment()
            fragment = fragmentSwap
        } else if (item.itemId == R.id.grades) {
            var fragmentSwap = StudentCourseGradesFragment()
            fragment = fragmentSwap
        }
        drawerLayout.closeDrawer(GravityCompat.START)
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
