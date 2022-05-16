package com.example.lamp.ui.teacher.courses_page.course_content.settings

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.commonFunctions.CommonFunctions
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseSettingsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*


class TeacherCourseSettingsFragment : Fragment() {

    lateinit var viewBinding: FragmentTeacherCourseSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            com.example.lamp.R.layout.fragment_teacher_course_settings,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {

        viewBinding.changeImageBtn.setOnClickListener {
            CommonFunctions.imagePick(this)
        }

        viewBinding.deleteImageBtn.setOnClickListener {
            viewBinding.courseImageView.setImageResource(R.drawable.ic_courses)
            //delete image from database
        }

        viewBinding.startDateTxt.setText(
            "" + calendar.get(Calendar.DAY_OF_MONTH)
                    + "/" + calendar.get(Calendar.MONTH)
                    + "/" + calendar.get(Calendar.YEAR)
        )
        viewBinding.startDateTxt.setOnClickListener {
            showDatePicker(viewBinding.startDateTxt)
        }
        viewBinding.endDateTxt.setText(
            "" + calendar.get(Calendar.DAY_OF_MONTH)
                    + "/" + calendar.get(Calendar.MONTH)
                    + "/" + calendar.get(Calendar.YEAR)
        )
        viewBinding.endDateTxt.setOnClickListener {
            showDatePicker(viewBinding.endDateTxt)
        }

        viewBinding.saveBtn.setOnClickListener {
            Toast.makeText(context, "saved successfully", Toast.LENGTH_SHORT).show()
            //insert in database
            requireActivity().supportFragmentManager.popBackStack()
        }

        viewBinding.courseCodeLinearLayout.setOnClickListener {
            copyTextToClipboard()
        }

        onBackPressed()

    }


    fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Do you want to save?")
                        .setMessage("")
                        .setNeutralButton("Cancel") { dialog, which ->
                            // Respond to neutral button press
                        }
                        .setNegativeButton("Discard") { dialog, which ->
                            Toast.makeText(context, "Changes discarded", Toast.LENGTH_SHORT).show()
                            requireActivity().supportFragmentManager.popBackStack()
                        }
                        .show()

                }
            })
    }

    fun copyTextToClipboard() {
        val textToCopy = viewBinding.courseCodeTextView.text
        val clipboardManager =
            getSystemService(requireContext(), ClipboardManager::class.java) as ClipboardManager
        val clipData = ClipData.newPlainText("text", textToCopy)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(context, "Course code copied to clipboard", Toast.LENGTH_LONG).show()
    }


    val calendar = Calendar.getInstance()

    @SuppressLint("SetTextI18n")
    fun showDatePicker(edText: EditText) {

        val datePicker = DatePickerDialog(
            requireContext(),
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