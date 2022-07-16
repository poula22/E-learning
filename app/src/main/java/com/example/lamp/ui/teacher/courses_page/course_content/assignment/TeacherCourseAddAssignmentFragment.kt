package com.example.lamp.ui.teacher.courses_page.course_content.assignment


import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.common_functions.CommonFunctions
import com.example.common_functions.CommonFunctions.Companion.calendar
import com.example.common_functions.DocumentAccessFragment
import com.example.domain.model.AssignmentResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseAddAssignmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.runBlocking
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.InputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.*

class TeacherCourseAddAssignmentFragment : DocumentAccessFragment() {
    lateinit var viewBinding: FragmentTeacherCourseAddAssignmentBinding
    lateinit var viewModel: TeacherCourseAddAssignmentViewModel
    var path:String?=null
    private var inputStream: InputStream?=null
    override fun showProgressBar() {
        return
    }

    override fun resultListener(byteArray: ByteArray) {
        //no3 el file??? png-pdf
        viewBinding.attachment.setText("File Attached")
        viewBinding.attachment.setBackgroundResource(R.color.dark_green)
        try {
            inputStream=fileUri?.let { requireActivity().contentResolver.openInputStream(it) }
        }catch (e:Exception){
            Log.v("Exception",e.toString())
        }
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(TeacherCourseAddAssignmentViewModel::class.java)
    }
    fun subscribeToLiveData(){
        viewModel.liveData.observe(viewLifecycleOwner){
            if (it.code()==200)
                requireActivity().supportFragmentManager.popBackStack()
            else
                Toast.makeText(requireContext(),"error",Toast.LENGTH_LONG).show()
        }
        viewModel.errorMessage.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_course_add_assignment,
            container,
            false
        )
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initViews()

    }

    private fun initViews() {
        if (calendar.get(Calendar.MONTH).plus(1) < 10) {
            viewBinding.startDateTxt.setText(
                "" + calendar.get(Calendar.YEAR) + "/" + "0" + calendar.get(Calendar.MONTH)
                    .plus(1) + "/"
                        + calendar.get(Calendar.DAY_OF_MONTH)
            )
            viewBinding.endDateTxt.setText(
                "" + calendar.get(Calendar.YEAR) + "/" + "0" + calendar.get(Calendar.MONTH)
                    .plus(1) + "/"
                        + calendar.get(Calendar.DAY_OF_MONTH)
            )
        } else {
            viewBinding.startDateTxt.setText(
                "" + calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH)
                    .plus(1) + "/"
                        + calendar.get(Calendar.DAY_OF_MONTH)
            )
            viewBinding.endDateTxt.setText(
                "" + calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH)
                    .plus(1) + "/"
                        + calendar.get(Calendar.DAY_OF_MONTH)
            )
        }

        viewBinding.startDateTxt.setOnClickListener {
            CommonFunctions.showDatePicker(viewBinding.startDateTxt, requireContext())
        }

        viewBinding.endDateTxt.setOnClickListener {
            CommonFunctions.showDatePicker(viewBinding.endDateTxt, requireContext())
        }

        viewBinding.addAttachmentBtn.setOnClickListener {
           uploadDoc()
//            val x = selectPdf()
//            viewBinding.attachment.text = x
//            Log.v("fragment", this.view.toString())



        }
//        fun Uri.getName(context: Context): String {
//            val returnCursor = context.contentResolver.query(this, null, null, null, null)
//            val nameIndex = returnCursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
//            returnCursor?.moveToFirst()
//            val fileName = returnCursor?.getString(nameIndex!!)
//            returnCursor?.close()
//            return fileName!!
//        }


        viewBinding.saveBtn.setOnClickListener {
            if (validateForm()) {
                val title = viewBinding.title.text.toString()
                val description = viewBinding.description.text.toString()
                val points = viewBinding.points.text.toString()
//                val datePattern = SimpleDateFormat("dd/MM/yyyy")

                val startDate =viewBinding.startDateTxt.text.toString().replace("/","-")+"T00:00:00Z"

//                datePattern.parse(
//                    viewBinding.endDateTxt.text.toString()
//                )
                val endDate =viewBinding.endDateTxt.text.toString().replace("/","-")+"T00:00:00Z"
                Log.v("date",startDate)

                Toast.makeText(context, "saved successfully", Toast.LENGTH_SHORT).show()
                //insert in database
                val assignment=AssignmentResponseDTO(
                    filePath,points.toInt()
                    ,description
                    ,null
                    , endDate
                    ,title, CONSTANTS.courseId
                    ,startDate
                )
                val file =
                    File.createTempFile(
                        "test",
                        ".pdf",
                        requireContext().cacheDir
                    )
                runBlocking {
                    FileUtils.copyInputStreamToFile(inputStream, file)
                }
                Log.v("file",file.toString())
                viewModel.addAssignment(assignment,file)
            }
        }
        CommonFunctions.onBackPressed(requireActivity(), viewLifecycleOwner, requireContext())
//        requireActivity()
//            .onBackPressedDispatcher
//            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    Log.d(TAG, "Fragment back pressed invoked")
//                    // Do custom work here
//                    MaterialAlertDialogBuilder(requireContext())
//                        .setTitle("Are you sure you want to discard changes?")
////                        .setMessage("")
//                        .setNeutralButton("Cancel") { dialog, which ->
//                            // Respond to neutral button press
//                        }
//                        .setNegativeButton("Discard") { dialog, which ->
//                            Toast.makeText(context, "Changes discarded", Toast.LENGTH_SHORT)
//                                .show()
//                            requireActivity().supportFragmentManager.popBackStack()
//                        }
//                        .show()
//
//
//                    // if you want onBackPressed() to be called as normal afterwards
//                    if (isEnabled) {
//                        isEnabled = false
//                        requireActivity().onBackPressed()
//                    }
//                }
//            }
//            )
    }

    // Intent for openning files
    fun selectPdf() : String?{
        val pdfIntent = Intent(Intent.ACTION_GET_CONTENT)
        pdfIntent.type = "application/pdf"
        pdfIntent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(pdfIntent, 12)
        return pdfIntent.data?.path

    }


    fun validateForm(): Boolean {
        var isValid = true
        if (viewBinding.titleLayout.editText?.text.toString().isBlank()) {
            viewBinding.titleLayout.error = "please enter assignment title"
            isValid = false
        } else {
            viewBinding.titleLayout.error = null
        }
        if (viewBinding.startDateTxt.text.toString().isBlank()) {
            viewBinding.startDateTxt.error = "please enter assignment start date"
            isValid = false
        } else {
            viewBinding.startDate.error = null
        }
        if (viewBinding.endDateTxt.text.toString().isBlank()) {
            viewBinding.endDateTxt.error = "please enter assignment end date"
            isValid = false
        } else {
            viewBinding.endDateTxt.error = null
        }

        if (viewBinding.pointsTxt.text.toString().isBlank()) {
            viewBinding.pointsTxt.error = "please enter assignment points"
            isValid = false
        } else {
            viewBinding.pointsTxt.error = null
        }
//
//        val dtf: DateTimeFormatter = DateTimeFormatterBuilder()
//            .parseCaseInsensitive()
//            .appendPattern("u/M/d")
//            .toFormatter(Locale.ENGLISH)
//
//        if(viewBinding.endDateTxt.text.let { LocalDateTime.parse(it,dtf) } <= viewBinding.startDateTxt.text.let { LocalDateTime.parse(it,dtf) }
//            || viewBinding.startDateTxt.text.let { LocalDateTime.parse(it,dtf)  } < LocalDateTime.now()) {
//            viewBinding.endDateTxt.error = "you should enter valid dates"
//            isValid = false
//        } else {
//            viewBinding.endDateTxt.error = null
//        }
//
        return isValid
    }


}