package com.example.lamp.ui.student.student_course_page.course_content.grades.assignments_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.AssignmentResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemGradeBinding

class AssignmentsGradesAdapter(var assignmentsGrades: MutableList<AssignmentResponseDTO>? = null) :
    RecyclerView.Adapter<AssignmentsGradesAdapter.AssignmentsGradesViewHolder>() {

    lateinit var viewBinding: ItemGradeBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AssignmentsGradesViewHolder {
        viewBinding = DataBindingUtil.inflate<ItemGradeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_grade,
            parent,
            false
        )
        return AssignmentsGradesViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return assignmentsGrades?.size ?: 0
    }

    override fun onBindViewHolder(holder: AssignmentsGradesViewHolder, position: Int) {
        var assignmentGrade = assignmentsGrades?.get(position)
        holder.viewBinding.title.text = assignmentGrade?.title.toString()
        holder.viewBinding.percentageTxt.text =
            assignmentGrade?.grade.toString() + "%" // divided by total grade * 100
        holder.viewBinding.progressPercentage.progress =
            assignmentGrade?.grade!!.toInt() // divided by total grade * 100
    }

    class AssignmentsGradesViewHolder(val viewBinding: ItemGradeBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {


    }
}