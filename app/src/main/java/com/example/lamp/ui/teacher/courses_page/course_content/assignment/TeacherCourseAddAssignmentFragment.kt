package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.commonFunctions.CommonFunctions
import com.example.commonFunctions.CommonFunctions.Companion.calendar
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseAddAssignmentBinding
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
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        viewBinding.startDateTxt.setText(
            "" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/"
                    + calendar.get(Calendar.YEAR)
        )
        viewBinding.startDateTxt.setOnClickListener {
            CommonFunctions.showDatePicker(viewBinding.startDateTxt, requireContext())
        }
        viewBinding.endDateTxt.setText(
            "" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/"
                    + calendar.get(Calendar.YEAR)
        )
        viewBinding.endDateTxt.setOnClickListener {
            CommonFunctions.showDatePicker(viewBinding.endDateTxt, requireContext())
        }

        viewBinding.addAttachmentBtn.setOnClickListener {
            CommonFunctions.uploadDoc(requireActivity())
        }
        viewBinding.saveBtn.setOnClickListener {
            Toast.makeText(context, "saved succesful", Toast.LENGTH_SHORT).show()
            //insert in database
            requireActivity().supportFragmentManager.popBackStack()
        }
        CommonFunctions.onBackPressed(requireActivity(), viewLifecycleOwner, requireContext())
    }
}