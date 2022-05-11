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
import com.example.recyclerviewpracticekotlin.CourseItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView


class TeacherCourseDetails(var course: CourseItem?) : Fragment() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewBinding: FragmentTeacherCourseDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater,
            R.layout.fragment_teacher_course_details,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity?)!!.setSupportActionBar(viewBinding.teacherCourseContainer.toolbar)
        val drawerLayout: DrawerLayout = viewBinding.drawerLayout
        val navView: NavigationView = viewBinding.navView
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        viewBinding.teacherCourseContainer.toolbar.setNavigationOnClickListener{
            drawerLayout.open()
        }

    }

    override fun onDetach() {
        super.onDetach()
        val bottomNavigationView: BottomNavigationView =
            requireActivity().findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.isVisible = true
        val floatingActionBtn: FloatingActionButton =requireActivity().findViewById(R.id.floating_action_btn)
        floatingActionBtn.isVisible=true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.teacher_course_details, menu)
    }

}