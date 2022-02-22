package com.example.lamp.ui.parent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.ui.student.student_course_page.CoursesFragment
import com.example.lamp.ui.student.student_features_page.FeaturesFragment
import com.example.lamp.ui.student.student_home_page.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ParentContainerFragment:Fragment() {
    lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parent_container_all_tabs,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        bottomNavigationView=requireView().findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener {    menuItem->
            if(menuItem.itemId==R.id.home){
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.parent_fragment_tab, HomeFragment())
                    .commit()
            }else if(menuItem.itemId==R.id.courses){
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.parent_fragment_tab, CoursesFragment())
                    .commit()
            }else if(menuItem.itemId==R.id.features){
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.parent_fragment_tab, FeaturesFragment())
                    .commit()
            }
            return@setOnItemSelectedListener true
        }
        bottomNavigationView.selectedItemId=R.id.home
    }
}