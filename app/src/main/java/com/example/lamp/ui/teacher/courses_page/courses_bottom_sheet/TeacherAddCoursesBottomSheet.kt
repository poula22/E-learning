package com.example.lamp.ui.teacher.courses_page.courses_bottom_sheet
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.lamp.R
import com.example.recyclerviewpracticekotlin.CourseItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout


class TeacherAddCoursesBottomSheet: BottomSheetDialogFragment() {
    lateinit var courseNameLayout: TextInputLayout
    lateinit var courseCodeLayout: TextInputLayout
    lateinit var teacherNameLayout: TextInputLayout
    lateinit var describtionLayout: TextInputLayout
    lateinit var addCourse: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teacher_add_course,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    fun initViews(){
        courseNameLayout=requireView().findViewById(R.id.course_name_layout)
        courseCodeLayout=requireView().findViewById(R.id.code_layout)
        teacherNameLayout=requireView().findViewById(R.id.teacher_name_layout)
        describtionLayout=requireView().findViewById(R.id.description_layout)
        addCourse=requireView().findViewById(R.id.add_course_btn)
        addCourse.setOnClickListener{
            if (validateForm()){
                val teacherName=teacherNameLayout.editText?.text.toString()
                val courseName=courseNameLayout.editText?.text.toString()
                val courseCode=courseCodeLayout.editText?.text.toString()
                val description=describtionLayout.editText?.text.toString()
                val course=CourseItem(courseName,teacherName,courseCode,description)
                dismiss()
            }
        }
    }
    fun validateForm():Boolean{
        var isValid=true
        if(courseNameLayout.editText?.text.toString().isBlank()){
            courseNameLayout.error="please enter todo details"
            isValid=false
        }
        else{
            courseNameLayout.error=null
        }
        if(courseCodeLayout.editText?.text.toString().isBlank()){
            courseCodeLayout.error="please enter todo details"
            isValid=false
        }
        else{
            courseCodeLayout.error=null
        }
        if(describtionLayout.editText?.text.toString().isBlank()){
            describtionLayout.error="please enter todo details"
            isValid=false
        }
        else{
            describtionLayout.error=null
        }
        if(teacherNameLayout.editText?.text.toString().isBlank()){
            teacherNameLayout.error="please enter todo details"
            isValid=false
        }
        else{
            teacherNameLayout.error=null
        }
        return isValid
    }

}