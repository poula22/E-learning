package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.commonFunctions.CommonFunctions
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseAddAssignmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*

class TeacherCourseAddAssignmentFragment : Fragment() {
    lateinit var viewBinding: FragmentTeacherCourseAddAssignmentBinding
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
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("do you want to save?")
                        .setMessage("")
                        .setNeutralButton("cancel") { dialog, which ->
                            // Respond to neutral button press
                        }
                        .setNegativeButton("discard") { dialog, which ->
                            requireActivity().supportFragmentManager.popBackStack()
                        }
                        .show()

                }
            })
    }

    private fun initViews() {
        viewBinding.startDateTxt.setText(
            "" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/"
                    + calendar.get(Calendar.YEAR)
        )
        viewBinding.startDateTxt.setOnClickListener {
            showDatePicker(viewBinding.startDateTxt)
        }
        viewBinding.endDateTxt.setText(
            "" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/"
                    + calendar.get(Calendar.YEAR)
        )
        viewBinding.endDateTxt.setOnClickListener {
            showDatePicker(viewBinding.endDateTxt)
        }

        viewBinding.addAttachmentBtn.setOnClickListener {
            CommonFunctions.uploadDoc(requireActivity())
        }
        viewBinding.saveBtn.setOnClickListener {
            Toast.makeText(context, "saved succesful", Toast.LENGTH_SHORT).show()
            //insert in database
            requireActivity().supportFragmentManager.popBackStack()
        }

    }

    val calendar: Calendar = Calendar.getInstance()
    private fun showDatePicker(edText: EditText) {

        val datePicker = DatePickerDialog(requireContext(),
            { view, year, month, dayOfMonth ->
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.YEAR, year)
                edText.setText("" + dayOfMonth + "/" + month.plus(1) + "/" + year)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

}