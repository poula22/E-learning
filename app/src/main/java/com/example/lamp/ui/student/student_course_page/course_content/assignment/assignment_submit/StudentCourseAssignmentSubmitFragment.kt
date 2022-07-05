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
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
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
import com.github.dhaval2404.imagepicker.ImagePicker
import org.apache.commons.io.FileUtils
import java.io.File

class StudentCourseAssignmentSubmitFragment:ExternalStorageAccessFragment() {
    lateinit var viewBinding:FragmentStudentCourseAssignmentSubmitBinding
    lateinit var viewModel:StudentCourseAssignmentSubmitViewModel
    var assignmentId:Int=-1
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
        var bundle=arguments
        var assignment=bundle?.getSerializable("assignment") as AssignmentDetailsResponseDTO
        assignmentId= assignment.id!!
        subscribeToLiveData()
        viewBinding.submitAssignmentBtn.setOnClickListener {
           upDoc()
        }
        viewBinding.uploadFile.setOnClickListener {
            startForImageResult.launch(CommonFunctions.uploadDoc(this.requireActivity()))
        }
    }

    private fun subscribeToLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "assignment uploaded successfully", Toast.LENGTH_SHORT).show()
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
            val inputStream=fileUri?.let { requireActivity().contentResolver.openInputStream(it) }
            val studentAnswer= AssignmentAnswerResponseDTO(CONSTANTS.user_id,filePath,filePath,null,null,null, assignmentId)
            val file =
                File.createTempFile(
                    "test",
                    ".pdf",
                    requireContext().cacheDir
                )
            FileUtils.copyInputStreamToFile(inputStream, file)
            if (file.exists() == true) {
            viewModel.submitAssignment(studentAnswer,file)
        }
        }catch (e:Exception){
            Log.v("Exception",e.toString())
        }



    }

    fun openDirectory(pickerInitialUri: Uri) {
        // Choose a directory using the system's file picker.
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE).apply {
            // Optionally, specify a URI for the directory that should be opened in
            // the system file picker when it loads.
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri)

        }
        startActivity(intent)
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