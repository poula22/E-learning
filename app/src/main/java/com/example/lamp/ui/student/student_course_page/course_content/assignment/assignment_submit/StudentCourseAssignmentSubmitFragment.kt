package com.example.lamp.ui.student.student_course_page.course_content.assignment.assignment_submit

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.commonFunctions.CONSTANTS
import com.example.commonFunctions.CommonFunctions
import com.example.data.model.AssignmentAnswerDetailsResponse
import com.example.domain.model.AssignmentAnswerResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCourseAssignmentSubmitBinding
import com.example.lamp.ui.student.student_course_page.course_content.assignment.AssignmentItem
import com.github.dhaval2404.imagepicker.ImagePicker

class StudentCourseAssignmentSubmitFragment:Fragment() {
    lateinit var viewBinding:FragmentStudentCourseAssignmentSubmitBinding
    lateinit var studentCourseAssignmentSubmitViewModel:StudentCourseAssignmentSubmitViewModel
    var uri:String?=null
    var assignmentId:Int=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        studentCourseAssignmentSubmitViewModel=ViewModelProvider(this).get(StudentCourseAssignmentSubmitViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_student_course_assignment_submit,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bundle=arguments
        var assignment=bundle?.getSerializable("assignment") as AssignmentAnswerDetailsResponse
        assignmentId= assignment.id!!
        viewBinding.submitAssignmentBtn.setOnClickListener {
            if(uri!=null){
                var studentAnswer=AssignmentAnswerResponseDTO(CONSTANTS.user_id,uri.toString(),uri.toString(),null,null,null, assignmentId)
                studentCourseAssignmentSubmitViewModel.submitAssignment(studentAnswer)
            }
        }
        viewBinding.uploadFile.setOnClickListener {
            startForImageResult.launch(CommonFunctions.uploadDoc(this.requireActivity()))
        }
    }

    val startForImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!

                var inputStream = requireActivity().contentResolver.openInputStream(fileUri)
                var byteArray = inputStream?.readBytes()

                // Show progress bar when request is in progress
//                requireActivity().window.setFlags(
//                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
//                )
                ///
                uri=fileUri.toString()
                uri?.let {
                    Log.v("uri",it)
                }
                viewBinding.uploadFile.text="Done"

            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
}