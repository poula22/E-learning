package com.example.lamp.ui.teacher.courses_page.course_content.assignment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.commonFunctions.CommonFunctions
import com.example.commonFunctions.CommonFunctions.Companion.calendar
import com.example.commonFunctions.DocumentAccessFragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseAddAssignmentBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_course_page.course_content.assignment.AssignmentItem
import java.text.SimpleDateFormat
import java.util.*

class TeacherCourseAddAssignmentFragment : DocumentAccessFragment() {
    lateinit var viewBinding: FragmentTeacherCourseAddAssignmentBinding

    override fun showProgressBar() {
        return
    }

    override fun resultListener(byteArray: ByteArray) {
        //send file path to backend
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

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
        initViews()
    }

    private fun initViews() {
        if (calendar.get(Calendar.MONTH).plus(1) < 10) {
            viewBinding.startDateTxt.setText(
                "" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + "0" + calendar.get(Calendar.MONTH)
                    .plus(1) + "/"
                        + calendar.get(Calendar.YEAR)
            )
            viewBinding.endDateTxt.setText(
                "" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + "0" + calendar.get(Calendar.MONTH)
                    .plus(1) + "/"
                        + calendar.get(Calendar.YEAR)
            )
        } else {
            viewBinding.startDateTxt.setText(
                "" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH)
                    .plus(1) + "/"
                        + calendar.get(Calendar.YEAR)
            )
            viewBinding.endDateTxt.setText(
                "" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH)
                    .plus(1) + "/"
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
            uploadDoc()
            Log.v("fragment", this.view.toString())
        }


        viewBinding.saveBtn.setOnClickListener {
            if (validateForm()) {
                var title = viewBinding.title.text.toString()
                var description = viewBinding.description.text.toString()
                var points = viewBinding.pointsTxt.text.toString()
                var datePattern = SimpleDateFormat("dd/MM/yyyy")

                val startDate = datePattern.parse(
                    viewBinding.startDateTxt.text.toString()
                )


                val endDate = datePattern.parse(
                    viewBinding.endDateTxt.text.toString()
                )

                Toast.makeText(context, "saved succesful", Toast.LENGTH_SHORT).show()
                //insert in database
                TestData.ASSIGNMENTS.add(
                    AssignmentItem(
                        title, description, startDate, endDate, points, 100,
                        TestData.ASSIGNMENT_FROM_STUDENT
                    )
                )
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        CommonFunctions.onBackPressed(requireActivity(), viewLifecycleOwner, requireContext())
    }

//    // Intent for openning files
//    fun selectPdf() {
//        val pdfIntent = Intent(Intent.ACTION_GET_CONTENT)
//        pdfIntent.type = "application/pdf"
//        pdfIntent.addCategory(Intent.CATEGORY_OPENABLE)
//        startActivityForResult(pdfIntent, 12)
//
//
//    }


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


}