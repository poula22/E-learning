package com.example.lamp.ui.teacher.courses_page.course_content.grades.students_page.students_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.AssignmentResponseDTO
import com.example.domain.model.QuizResponseDTO
import com.example.domain.model.StudentResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemGradeBinding

class TeacherStudentsOverallGradesAdapter(var studentsOverallGrades: MutableList<StudentResponseDTO>? = null) :
    RecyclerView.Adapter<TeacherStudentsOverallGradesAdapter.StudentsOverallGradesViewHolder>() {

    lateinit var viewBinding: ItemGradeBinding


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentsOverallGradesViewHolder {
        viewBinding = DataBindingUtil.inflate<ItemGradeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_grade,
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
        holder.viewBinding.title.text =
            studentOverallGrade?.firstName + " " + studentOverallGrade?.lastName
    }


    class StudentsOverallGradesViewHolder(val viewBinding: ItemGradeBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {


    }


    var onStudentClickListener: OnStudentClickListener? = null

    interface OnStudentClickListener {
        fun setOnStudentClickListener(
            assignment: MutableList<AssignmentResponseDTO>,
            quiz: MutableList<QuizResponseDTO>
        )
    }


}