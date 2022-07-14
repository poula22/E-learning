package com.example.lamp.ui.student.student_course_page.course_content.assignment.assignment_submit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.common_functions.CommonFunctions
import com.example.common_functions.DocumentAccessFragment
import com.example.common_functions.ExternalStorageAccessFragment
import com.example.domain.model.AssignmentAnswerResponseDTO
import com.example.domain.model.AssignmentDetailsResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCourseAssignmentSubmitBinding
import com.example.lamp.ui.teacher.courses_page.course_content.assignment.PDFViewer
import com.github.dhaval2404.imagepicker.ImagePicker
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.InputStream

class StudentCourseAssignmentSubmitFragment:ExternalStorageAccessFragment() {
    lateinit var viewBinding:FragmentStudentCourseAssignmentSubmitBinding
    lateinit var viewModel:StudentCourseAssignmentSubmitViewModel
    var assignmentId:Int=-1
    var inputStream: InputStream?=null
    var pdfPath:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(StudentCourseAssignmentSubmitViewModel::class.java)
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
        val bundle=arguments
        val assignment=bundle?.getSerializable("assignment") as AssignmentDetailsResponseDTO
        assignmentId= assignment.id!!
        subscribeToLiveData()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAssignmentDetails(assignmentId)
    }

    private fun initViews() {
        viewBinding.assignmentTxt.setOnClickListener {
            if(pdfPath!=null){
                val bundle=Bundle()
                val pdfViewer=PDFViewer()
                bundle.putString("pdf",pdfPath)
                pdfViewer.arguments=bundle
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.student_course_content_container,pdfViewer)
                    .commit()
            }
        }
        viewBinding.pdfIcon.setOnClickListener {
            if(pdfPath!=null){
                val bundle=Bundle()
                val pdfViewer=PDFViewer()
                bundle.putString("pdf",pdfPath)
                pdfViewer.arguments=bundle
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.student_course_content_container,pdfViewer)
                    .commit()
            }
        }
        viewBinding.submitAssignmentBtn.setOnClickListener {
            val studentAnswer= AssignmentAnswerResponseDTO(CONSTANTS.user_id,null,null,null,null,null, assignmentId)
            if (inputStream!=null){
                val file =
                    File.createTempFile(
                        "assignment",
                        ".pdf",
                        requireContext().cacheDir
                    )
                FileUtils.copyInputStreamToFile(inputStream, file)
                studentAnswer.fileName=file.name
                viewModel.submitAssignment(studentAnswer,file)

            }else{
                Toast.makeText(requireContext(), "please attach assignment answer first", Toast.LENGTH_SHORT).show()
            }

        }
        viewBinding.uploadFile.setOnClickListener {
            uploadDoc()
        }
    }

    private fun subscribeToLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "assignment uploaded successfully", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()
        }
        viewModel.assignmentInfoLiveData.observe(viewLifecycleOwner){
            if (it.filePath!=null){
                pdfPath=it.filePath
            }else{
                viewBinding.pdfIcon.isVisible=false
                viewBinding.assignmentTxt.isVisible=false
            }

            if (it.description!=null){
                viewBinding.assignmentNotesTxt.setText(it.description)
            }else{
                viewBinding.assignmentNotesTxt.isVisible=false
            }



        }
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showProgressBar() {

    }

    override fun resultListener(byteArray: ByteArray) {

//        val path= "file://"+filePath
//        val file= path.let { File(it) }
//        Log.v("File path", path)
//        if (file.exists() == true) {
//            viewModel.submitAssignment(studentAnswer,file)
//        }

        try {
            inputStream=fileUri?.let { requireActivity().contentResolver.openInputStream(it) }
            requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }catch (e:Exception){
            Log.v("Exception",e.toString())
        }



    }


//    val startForImageResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
//            val resultCode = result.resultCode
//            val data = result.data
//
//            if (resultCode == Activity.RESULT_OK) {
//                //Image Uri will not be null for RESULT_OK
//                val fileUri = data?.data!!
//
//                var inputStream = requireActivity().contentResolver.openInputStream(fileUri)
//                var byteArray = inputStream?.readBytes()
//
//                // Show progress bar when request is in progress
////                requireActivity().window.setFlags(
////                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
////                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
////                )
//                ///
//                uri=fileUri.toString()
//                uri?.let {
//                    Log.v("uri",it)
//                }
//                viewBinding.uploadFile.text="Done"
//
//            } else if (resultCode == ImagePicker.RESULT_ERROR) {
//                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
//                    .show()
//            } else {
//                Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show()
//            }
//        }
}