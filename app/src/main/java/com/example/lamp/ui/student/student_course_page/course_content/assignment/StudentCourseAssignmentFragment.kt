package com.example.lamp.ui.student.student_course_page.course_content.assignment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCourseAssignmentBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_course_page.course_content.assignment.assignment_recycler_view.StudentCourseAssignmentAdapter
import com.example.lamp.ui.student.student_features_page.recitation.recite_paragraph.ReciteParagraphFragment
import com.example.lamp.ui.student.student_features_page.recitation.recite_words.ReciteWordsFragment
import com.google.android.material.tabs.TabLayout

class StudentCourseAssignmentFragment(var assignments: MutableList<AssignmentItem>? = null) :
    Fragment() {
    lateinit var viewBinding: FragmentStudentCourseAssignmentBinding
    lateinit var adapter: StudentCourseAssignmentAdapter
    lateinit var tabLayout: TabLayout

    private fun initTabs(
        all: TabLayout.Tab,
        submitted: TabLayout.Tab,
        notSubmitted: TabLayout.Tab
    ) {
        all.text = "All"
        submitted.text = "Submitted"
        notSubmitted.text = "Not Submitted"
        tabLayout.addTab(all)
        tabLayout.addTab(submitted)
        tabLayout.addTab(notSubmitted)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_student_course_assignment,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= StudentCourseAssignmentAdapter(assignments)
        viewBinding.studentAssignmentsRv.adapter=adapter
        tabLayout = viewBinding.tabLayout
        val all = tabLayout.newTab()
        val submitted = tabLayout.newTab()
        val notSubmitted = tabLayout.newTab()
        initTabs(all, submitted, notSubmitted)
        tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    Log.v("action;::",tab?.text.toString())
                    filterList(tab?.text.toString())
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    Log.v("action;::",tab?.text.toString())
                    filterList(tab?.text.toString())
                }


            }
        )
        tabLayout.selectTab(all)
    }


    fun filterList(text: String) {
        if (text.isEmpty()) {
            adapter.setFilteredList(assignments!!)
        }
        //filter list here
        val filteredList = mutableListOf<AssignmentItem>()
        assignments?.let {
            for (item in it) {
                if (item.state.lowercase() == text.lowercase()) {
                    filteredList.add(item) //add to filtered list
                } else if(text.lowercase() == "all" ){
                    filteredList.add(item) // add all the items that are not filtered
                }
            }

        }

        if (filteredList.isNotEmpty()) {
            adapter.setFilteredList(filteredList)
        }
    }


}