package com.example.lamp.ui.teacher.courses_page.course_content.grades.students_page.students_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.StudentResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherStudentGradeBinding

class TeacherStudentsOverallGradesAdapter(var studentsOverallGrades: List<StudentResponseDTO>? = null) :
    RecyclerView.Adapter<TeacherStudentsOverallGradesAdapter.StudentsOverallGradesViewHolder>() {

    lateinit var viewBinding: ItemTeacherStudentGradeBinding


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentsOverallGradesViewHolder {
        viewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_teacher_student_grade,
            parent,
            false
        )
        return StudentsOverallGradesViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return studentsOverallGrades?.size ?: 0
    }

    override fun onBindViewHolder(holder: StudentsOverallGradesViewHolder, position: Int) {
        var studentOverallGrade = studentsOverallGrades?.get(position)
        holder.viewBinding.studentName.text =
            studentOverallGrade?.firstName + " " + studentOverallGrade?.lastName

        holder.viewBinding.card.setOnClickListener {
            studentsOverallGrades?.let { it1 ->
                onStudentClickListener?.setOnStudentClickListener(
                    it1.get(
                        position
                    )
                )
            }
        }

    }


    class StudentsOverallGradesViewHolder(val viewBinding: ItemTeacherStudentGradeBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {


    }


    fun changeData(studentsList: List<StudentResponseDTO>) {
        this.studentsOverallGrades = studentsList
        notifyDataSetChanged()
    }

    var onStudentClickListener: OnStudentClickListener? = null

    interface OnStudentClickListener {
        fun setOnStudentClickListener(
            student: StudentResponseDTO
        )
    }


}