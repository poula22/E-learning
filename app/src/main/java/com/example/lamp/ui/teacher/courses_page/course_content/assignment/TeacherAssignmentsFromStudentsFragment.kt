package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toFile
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.data.model.AssignmentDetailsResponse
import com.example.data.model.AssignmentResponse
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherAssignmentsFromStudentsBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_course_page.course_content.assignment.AssignmentItem
import com.example.lamp.ui.teacher.courses_page.course_content.assignment.assignments_from_students.TeacherAssignmentsFromStudentsAdapter
import java.io.File
import java.util.*

class TeacherAssignmentsFromStudentsFragment(val assignment : AssignmentResponse?=null) : Fragment() {
    // get assignment id --> request assignment details -> assign assignment details to adapter
    lateinit var viewBinding: FragmentTeacherAssignmentsFromStudentsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_assignments_from_students,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        //"android.resource://" + requireActivity().packageName  + "/"+R.raw.dragon_ball,)
//        var uri=Uri.parse("android.resource://"+resources.getResourceName(R.raw.teacher_profile_25_4))
        val adapter = TeacherAssignmentsFromStudentsAdapter(TestData.ASSIGNMENT_FROM_STUDENT)
        adapter.onPdfOpenListener=object :TeacherAssignmentsFromStudentsAdapter.OnPdfOpenListener{
            override fun onPdfOpen() {
                requireActivity().supportFragmentManager.beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.container,PDFViewer())
                    .commit()
            }

        }
        viewBinding.assignmentsFromStudentsRv.adapter= adapter
        //assign assignment details to adapter
        viewBinding.item=assignment


    }


}