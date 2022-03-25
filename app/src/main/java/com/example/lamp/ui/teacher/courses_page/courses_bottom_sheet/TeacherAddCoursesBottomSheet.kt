package com.example.lamp.ui.teacher.courses_page.courses_bottom_sheet
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherAddCourseBinding
import com.example.lamp.databinding.FragmentTeacherCoursesBinding
import com.example.recyclerviewpracticekotlin.CourseItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout


class TeacherAddCoursesBottomSheet: BottomSheetDialogFragment() {
    lateinit var teacherAddCourseBinding:FragmentTeacherAddCourseBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        teacherAddCourseBinding = DataBindingUtil.inflate<FragmentTeacherAddCourseBinding>(inflater,R.layout.fragment_teacher_add_course,container,false)
        return teacherAddCourseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    fun initViews(){

        teacherAddCourseBinding.addCourseBtn.setOnClickListener{
            if (validateForm()){
                val teacherName=teacherAddCourseBinding.teacherNameLayout.editText?.text.toString()
                val courseName=teacherAddCourseBinding.courseNameLayout.editText?.text.toString()
                val courseCode=teacherAddCourseBinding.codeLayout.editText?.text.toString()
                val description=teacherAddCourseBinding.descriptionLayout.editText?.text.toString()
                val course=CourseItem(courseName,teacherName,courseCode,description)
                dismiss()
            }
        }
    }
    fun validateForm():Boolean{
        var isValid=true
        if(teacherAddCourseBinding.courseNameLayout.editText?.text.toString().isBlank()){
            teacherAddCourseBinding.courseNameLayout.error="please enter todo details"
            isValid=false
        }
        else{
            teacherAddCourseBinding.courseNameLayout.error=null
        }
        if(teacherAddCourseBinding.codeLayout.editText?.text.toString().isBlank()){
            teacherAddCourseBinding.codeLayout.error="please enter todo details"
            isValid=false
        }
        else{
            teacherAddCourseBinding.codeLayout.error=null
        }
        if(teacherAddCourseBinding.descriptionLayout.editText?.text.toString().isBlank()){
            teacherAddCourseBinding.descriptionLayout.error="please enter todo details"
            isValid=false
        }
        else{
            teacherAddCourseBinding.descriptionLayout.error=null
        }
        if(teacherAddCourseBinding.teacherNameLayout.editText?.text.toString().isBlank()){
            teacherAddCourseBinding.teacherNameLayout.error="please enter todo details"
            isValid=false
        }
        else{
            teacherAddCourseBinding.teacherNameLayout.error=null
        }
        return isValid
    }

}