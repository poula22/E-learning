package com.example.lamp.ui.student.student_course_page.course_content.assignment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.domain.model.AssignmentDetailsResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCourseAssignmentBinding
import com.example.lamp.ui.student.student_course_page.course_content.assignment.assignment_recycler_view.StudentCourseAssignmentAdapter
import com.example.lamp.ui.student.student_course_page.course_content.assignment.assignment_submit.StudentCourseAssignmentSubmitFragment
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.time.LocalDateTime

//filter fun - new json
class StudentCourseAssignmentFragment :
    Fragment() {
    lateinit var viewBinding: FragmentStudentCourseAssignmentBinding
    lateinit var adapter: StudentCourseAssignmentAdapter
    lateinit var tabLayout: TabLayout
    lateinit var viewModel: StudentCourseAssignmentViewModel
    val courseId:Int = CONSTANTS.courseId

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
        subscribeToLiveData()
        getAllAssignments()
    }

    private fun getAllAssignments() {
        viewModel.getData(courseId)
    }

    private fun subscribeToLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner){assignmentList ->
            val swap=assignmentList.toMutableList()
            assignmentList.forEach { assignment ->
                assignment.assignedGrade?.let {
                 if (it>0){
                     swap.remove(assignment)
                 }
                }
            }
            updateAssignmentsList(swap)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner){
            viewModel.errorMessage.value=it
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateAssignmentsList(assignmentsList: List<AssignmentDetailsResponseDTO>) {
        adapter.setFilteredList(assignmentsList.toMutableList())
    }

    private fun initViews() {
        adapter= StudentCourseAssignmentAdapter()
        adapter.onStudentAssignmentClickedListener=object :StudentCourseAssignmentAdapter.OnStudentAssignmentClickedListener{
            override fun onAssignmentClick(postion: Int) {
                goToAssignmentSubmit(postion)
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
                    getSelectedTabContent(tab)
                }
            }
        )
        tabLayout.selectTab(all)

    }

    private fun getSelectedTabContent(tab: TabLayout.Tab?) {
        val text=tab?.text.toString()
        if (text.isNotEmpty()) {
            val list=viewModel.filterList(text)
            adapter.setFilteredList(list)
        }
    }


    private fun goToAssignmentSubmit(postion: Int) {
        val assignment: AssignmentDetailsResponseDTO? =viewModel.liveData.value?.get(postion)
        val current = LocalDateTime.now()
        val output = assignment?.endTime?.let { LocalDateTime.parse(it) }
        if (output!= null) {
            if (current.isAfter(output)) {
                Toast.makeText(requireContext(), "wait for assignment to be graded", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = Bundle()
                bundle.putSerializable("assignment", assignment)
                val fragment = StudentCourseAssignmentSubmitFragment()
                fragment.arguments = bundle
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(this@StudentCourseAssignmentFragment.id, fragment)
                    .addToBackStack("")
                    .commit()
            }

        }else{
            val bundle = Bundle()
            bundle.putSerializable("assignment", assignment)
            val fragment = StudentCourseAssignmentSubmitFragment()
            fragment.arguments = bundle
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(this@StudentCourseAssignmentFragment.id, fragment)
                .addToBackStack("")
                .commit()
        }


    }
}