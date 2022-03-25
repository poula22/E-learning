package com.example.lamp.ui.parent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentParentContainerAllTabsBinding
import com.example.lamp.ui.parent.parent_children_page.ChildrenFragment
import com.example.lamp.ui.parent.parent_communicate_page.CommunicateFragment
import com.example.lamp.ui.parent.parent_courses_page.CoursesFragment
import com.example.lamp.ui.parent.parent_home_page.HomeFragment

class ParentContainerFragment:Fragment() {
    lateinit var parentContainerAllTabsBinding: FragmentParentContainerAllTabsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parentContainerAllTabsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_parent_container_all_tabs,container,false)
        return parentContainerAllTabsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        parentContainerAllTabsBinding.parentBottomNaviagationView.setOnItemSelectedListener { menuItem->
            if(menuItem.itemId==R.id.home){
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.parent_fragment_tab, HomeFragment())
                    .commit()
            }else if(menuItem.itemId==R.id.children){
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.parent_fragment_tab, ChildrenFragment())
                    .commit()
            }else if(menuItem.itemId==R.id.courses){
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.parent_fragment_tab, CoursesFragment())
                    .commit()
            }else if(menuItem.itemId==R.id.communicate){
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.parent_fragment_tab, CommunicateFragment())
                    .commit()
            }
            return@setOnItemSelectedListener true
        }
        parentContainerAllTabsBinding.parentBottomNaviagationView.selectedItemId=R.id.home
    }
}