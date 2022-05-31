package com.example.lamp.ui.student.student_course_page.course_content.assignment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCourseAssignmentBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_course_page.course_content.assignment.assignment_recycler_view.StudentCourseAssignmentAdapter
import com.example.lamp.ui.student.student_course_page.course_content.assignment.assignment_submit.StudentCourseAssignmentSubmitFragment
import com.example.lamp.ui.student.student_features_page.recitation.recite_paragraph.ReciteParagraphFragment
import com.example.lamp.ui.student.student_features_page.recitation.recite_words.ReciteWordsFragment
import com.google.android.material.tabs.TabLayout

class StudentCourseAssignmentFragment :
    Fragment() {
    lateinit var viewBinding: FragmentStudentCourseAssignmentBinding
    lateinit var adapter: StudentCourseAssignmentAdapter
    lateinit var tabLayout: TabLayout
    lateinit var viewModel: StudentCourseAssignmentViewModel

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(StudentCourseAssignmentViewModel::class.java)
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
        initViews()
        subscibeToLiveData()
        viewModel.getData(requireArguments())

    }

    private fun subscibeToLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner){
            adapter.setFilteredList(it)
        }
    }

    private fun initViews() {
        adapter= StudentCourseAssignmentAdapter()
        adapter.onStudentAssignmentClickedListener=object :StudentCourseAssignmentAdapter.OnStudentAssignmentClickedListener{
            override fun onAssignmentClick(postion: Int) {
                var bundle=Bundle()
                var assignment: AssignmentItem? =viewModel.liveData.value?.get(postion)
                bundle.putSerializable("assignment",assignment)
                var fragment=StudentCourseAssignmentSubmitFragment()
                fragment.arguments=bundle
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(this@StudentCourseAssignmentFragment.id,fragment)
                    .addToBackStack("")
                    .commit()
            }
        }
        viewBinding.studentAssignmentsRv.adapter=adapter
        tabLayout = viewBinding.tabLayout
        val all = tabLayout.newTab()
        val submitted = tabLayout.newTab()
        val notSubmitted = tabLayout.newTab()
        initTabs(all, submitted, notSubmitted)
        tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    var text=tab?.text.toString()
                    Log.v("action;::",text)
                    if (text.isNotEmpty()) {
                        var list=viewModel.filterList(text)
                        adapter.setFilteredList(list)
                    }

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    Log.v("action;::",tab?.text.toString())
                    var text=tab?.text.toString()
                    if (text.isNotEmpty()) {
                        var list=viewModel.filterList(text)
                        adapter.setFilteredList(list)
                    }
                }
            }
        )
        tabLayout.selectTab(all)

    }





}