package com.example.lamp.ui.teacher.courses_page.course_content.settings

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CommonFunctions
import com.example.common_functions.CommonFunctions.Companion.calendar
import com.example.data.model.CourseResponse
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseSettingsBinding
import java.util.*


class TeacherCourseSettingsFragment : Fragment() {

    lateinit var viewBinding: FragmentTeacherCourseSettingsBinding
    lateinit var course:CourseResponse
    lateinit var viewModel: TeacherCourseSettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(TeacherCourseSettingsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_course_settings,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        course=requireArguments().getSerializable("course") as CourseResponse
        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {

        viewBinding.changeImageBtn.setOnClickListener {
//            CommonFunctions.imagePick(this)
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
            CommonFunctions.showDatePicker(viewBinding.startDateTxt, requireContext())
        }
        viewBinding.endDateTxt.setText(
            "" + calendar.get(Calendar.DAY_OF_MONTH)
                    + "/" + calendar.get(Calendar.MONTH)
                    + "/" + calendar.get(Calendar.YEAR)
        )
        viewBinding.endDateTxt.setOnClickListener {
            CommonFunctions.showDatePicker(viewBinding.endDateTxt, requireContext())
        }

        viewBinding.saveBtn.setOnClickListener {
            Toast.makeText(context, "saved successfully", Toast.LENGTH_SHORT).show()
            //insert in database
            requireActivity().supportFragmentManager.popBackStack()
        }

        viewBinding.courseCodeLinearLayout.setOnClickListener {
            CommonFunctions.copyTextToClipboard(
                viewBinding.courseCodeTextView.text.toString(),
                requireContext()
            )
        }
        CommonFunctions.onBackPressed(requireActivity(), viewLifecycleOwner, requireContext())
    }


}


