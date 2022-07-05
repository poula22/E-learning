package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.AssignmentAnswerDetailsResponseDTO
import com.example.domain.model.AssignmentAnswerResponseDTO
import com.example.domain.model.AssignmentResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherAssignmentsFromStudentsBinding
import com.example.lamp.ui.teacher.courses_page.course_content.assignment.assignments_from_students.TeacherAssignmentsFromStudentsAdapter

class TeacherAssignmentsFromStudentsFragment(val assignment : AssignmentResponseDTO?=null) : Fragment() {
    lateinit var viewBinding: FragmentTeacherAssignmentsFromStudentsBinding
    lateinit var adapter: TeacherAssignmentsFromStudentsAdapter
    lateinit var viewModel: TeacherAssignmentsFromStudentsViewModel
    lateinit var answersMutableList:MutableList<AssignmentAnswerResponseDTO>
    val assignmentId=assignment?.id!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(TeacherAssignmentsFromStudentsViewModel::class.java)
    }
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
        subscribeToLiveData()
        initViews()
        viewModel.getAllAssignmentAnswers(assignmentId)
    }

    private fun subscribeToLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner){
            adapter.changeData(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllAssignmentAnswers(assignmentId)
    }
    private fun initViews() {
        //"android.resource://" + requireActivity().packageName  + "/"+R.raw.dragon_ball,)
//        var uri=Uri.parse("android.resource://"+resources.getResourceName(R.raw.teacher_profile_25_4))
        answersMutableList= mutableListOf()
        adapter = TeacherAssignmentsFromStudentsAdapter(totalPoints = assignment?.grade ?:0)
        adapter.onPdfOpenListener=object :TeacherAssignmentsFromStudentsAdapter.OnPdfOpenListener{
            override fun onPdfOpen(pdf:String?) {
                val bundle=Bundle()
                bundle.putString("pdf",pdf)
                val pdfViewer=PDFViewer()
                pdfViewer.arguments=bundle
                requireActivity().supportFragmentManager.beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.container,pdfViewer)
                    .commit()
            }

        }
        adapter.onGradesSubmitListener=object :TeacherAssignmentsFromStudentsAdapter.OnGradesSubmitListener{
            override fun onGradeSubmit(
                assignmentAnswerDetails: AssignmentAnswerDetailsResponseDTO,
                grade: Int
            ) {
                val assignmentAnswer= AssignmentAnswerResponseDTO(
                    assignmentAnswerDetails.studentId,
                    assignmentAnswerDetails.studentFirstName,
                    assignmentAnswerDetails.pdf,
                    assignmentAnswerDetails.submitDate,
                    assignmentAnswerDetails.submitDate,
                    assignmentAnswerDetails.id,
                    assignmentAnswerDetails.assignmentId,
                    grade
                )
                viewModel.updateAssignmentAnswer(assignmentId,assignmentAnswer)
                viewModel.getAllAssignmentAnswers(assignmentId)
            }

        }
        viewBinding.assignmentsFromStudentsRv.adapter= adapter
        //assign assignment details to adapter
        viewBinding.item=assignment



    }


}