package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.icu.util.LocaleData
import android.os.Build
import android.text.format.DateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.commonFunctions.CommonFunctions
import com.example.commonFunctions.CommonFunctions.Companion.calendar
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseAddAssignmentBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_course_page.course_content.assignment.AssignmentItem
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ofPattern
import java.util.*

class TeacherCourseAddAssignmentFragment : Fragment() {
    lateinit var viewBinding: FragmentTeacherCourseAddAssignmentBinding
    var filePath:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    private fun initViews() {
        if (calendar.get(Calendar.MONTH).plus(1)<10){
            viewBinding.startDateTxt.setText(
                "" + calendar.get(Calendar.DAY_OF_MONTH) + "/" +"0"+ calendar.get(Calendar.MONTH).plus(1)+ "/"
                        + calendar.get(Calendar.YEAR)
            )
            viewBinding.endDateTxt.setText(
                "" + calendar.get(Calendar.DAY_OF_MONTH) + "/" +"0"+ calendar.get(Calendar.MONTH).plus(1) + "/"
                        + calendar.get(Calendar.YEAR)
            )
        }
        else
        {
            viewBinding.startDateTxt.setText(
                "" + calendar.get(Calendar.DAY_OF_MONTH) + "/"+ calendar.get(Calendar.MONTH).plus(1)+ "/"
                        + calendar.get(Calendar.YEAR)
            )
            viewBinding.endDateTxt.setText(
                "" + calendar.get(Calendar.DAY_OF_MONTH) + "/"+ calendar.get(Calendar.MONTH).plus(1) + "/"
                        + calendar.get(Calendar.YEAR)
            )
        }

        viewBinding.startDateTxt.setOnClickListener {
            CommonFunctions.showDatePicker(viewBinding.startDateTxt, requireContext())
        }

        viewBinding.endDateTxt.setOnClickListener {
            CommonFunctions.showDatePicker(viewBinding.endDateTxt, requireContext())
        }

        viewBinding.addAttachmentBtn.setOnClickListener {
            CommonFunctions.uploadDoc(requireActivity())
        }

        viewBinding.addAttachmentBtn.setOnClickListener {
            val intentDocument = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "*/*"
                putExtra(
                    Intent.EXTRA_MIME_TYPES, arrayOf(
                        "application/pdf"
                    )
                )
            }
            startForFileResult.launch(intentDocument)
        }
        viewBinding.saveBtn.setOnClickListener {
            if(validateForm()){
                var title=viewBinding.title.text.toString()
                var description=viewBinding.description.text.toString()
                var points=viewBinding.pointsTxt.text.toString()
                var datePattern=SimpleDateFormat("dd/MM/yyyy")

                val startDate = datePattern.parse(
                    viewBinding.startDateTxt.text.toString()
                )


                val endDate = datePattern.parse(
                    viewBinding.endDateTxt.text.toString())

                Toast.makeText(context, "saved succesful", Toast.LENGTH_SHORT).show()
                //insert in database
                TestData.ASSIGNMENTS.add(AssignmentItem(title,description,startDate,endDate,points,100,
                    TestData.ASSIGNMENT_FROM_STUDENT))
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        CommonFunctions.onBackPressed(requireActivity(), viewLifecycleOwner, requireContext())
    }

    override fun onStart() {
        super.onStart()
        var toolbar:Toolbar=requireActivity().findViewById(R.id.toolbar)
        toolbar.isVisible=false
        var drawerLayout:DrawerLayout=requireActivity().findViewById(R.id.drawer_layout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
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
        return isValid
    }
    val startForFileResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!
                filePath=fileUri.path

            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

}