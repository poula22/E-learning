package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.domain.model.AssignmentResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseAssignmentBinding
import com.example.lamp.ui.teacher.courses_page.course_content.assignment.assignment_recycler_view.TeacherCourseAssignmentAdapter


class TeacherCourseAssignmentFragment : Fragment() {
    //
    lateinit var viewBinding: FragmentTeacherCourseAssignmentBinding
    lateinit var viewModel: TeacherCourseAssignmentViewModel
    lateinit var adapter: TeacherCourseAssignmentAdapter
    val courseId = CONSTANTS.courseId
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeacherCourseAssignmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_course_assignment,
            container,
            false
        )
        return viewBinding.root
    }

    private fun subscribeToViewModel() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            adapter.changeData(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToViewModel()
        initViews()
        viewModel.getAllAssignments(courseId)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllAssignments(CONSTANTS.courseId)
    }

    private fun initViews() {
//        var dataList = mutableListOf<String>()
//        TestData.ASSIGNMENTS.forEach {
//            dataList.add(it.title)
//        }
        adapter = TeacherCourseAssignmentAdapter()
        viewBinding.assignmentRecyclerView.adapter = adapter
        val adapter = viewBinding.assignmentRecyclerView.adapter as TeacherCourseAssignmentAdapter
        adapter.onAssignmentClickListener =
            object : TeacherCourseAssignmentAdapter.OnAssignmentClickListener {
                override fun setOnAssignmentClickListener(assignment: AssignmentResponseDTO) {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .addToBackStack("")
                        .replace(
                            R.id.teacher_fragment_tab,
                            TeacherAssignmentsFromStudentsFragment(assignment)
                        )
                        .commit()
                }
            }

        viewBinding.addBtn.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .addToBackStack("")
                .replace(
                    R.id.teacher_fragment_tab,
                    TeacherCourseAddAssignmentFragment()
                )
                .commit()
        }
    }


}