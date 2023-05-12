package com.example.lamp.ui.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentContainerAllTabsBinding
import com.example.lamp.ui.student.student_course_page.CoursesFragment
import com.example.lamp.ui.student.student_features_page.FeaturesFragment
import com.example.lamp.ui.student.student_home_page.StudentHomeFragment
import com.example.lamp.ui.student.student_profile_page.ProfileFragment
import com.example.lamp.ui.student.student_website_page.WebSitesFragment


class StudentContainerFragment:Fragment() {
    lateinit var viewBinding:FragmentStudentContainerAllTabsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater
            ,R.layout.fragment_student_container_all_tabs
            ,container
            ,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewBinding.bottomNavigationView.setOnItemSelectedListener {    menuItem->
            goToFragment(menuItem)
            return@setOnItemSelectedListener true
        }
        //home menu
       viewBinding.bottomNavigationView.selectedItemId=R.id.home
    }

    private fun goToFragment(menuItem: MenuItem) {
        if(menuItem.itemId==R.id.home){
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.studentFragmentTab.id,StudentHomeFragment())
                .commit()
        }else if(menuItem.itemId==R.id.courses){
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.studentFragmentTab.id,CoursesFragment())
                .commit()
        }else if(menuItem.itemId==R.id.features){
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.studentFragmentTab.id, FeaturesFragment())
                .commit()
        }else if(menuItem.itemId==R.id.websites){
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.studentFragmentTab.id, WebSitesFragment())
                .commit()
        } else if(menuItem.itemId==R.id.profile){
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.studentFragmentTab.id, ProfileFragment())
                .commit()
        }
    }
}