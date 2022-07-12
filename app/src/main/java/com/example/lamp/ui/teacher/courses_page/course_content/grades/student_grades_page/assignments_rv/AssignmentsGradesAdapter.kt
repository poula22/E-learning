package com.example.lamp.ui.teacher.courses_page.course_content.grades.student_grades_page.assignments_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.AssignmentResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemGradeBinding

class AssignmentsGradesAdapter(var assignmentsGrades: List<AssignmentResponseDTO>? = null) :
    RecyclerView.Adapter<AssignmentsGradesAdapter.AssignmentsGradesViewHolder>() {

    lateinit var viewBinding: ItemGradeBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AssignmentsGradesViewHolder {
        viewBinding = DataBindingUtil.inflate(
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
        if (assignmentGrade?.grade != null) {
            holder.viewBinding.percentageTxt.text = assignmentGrade.grade.toString() + "%"
            holder.viewBinding.progressPercentage.progress =
                assignmentGrade.grade!!.toInt() //TODO: divide by todo * 100
            holder.viewBinding.notGraded.isVisible = false
        } else {
            holder.viewBinding.notGraded.isVisible = true
            holder.viewBinding.percentageTxt.isVisible = false
            holder.viewBinding.progressPercentage.isVisible = false
        }

//        holder.viewBinding.percentageTxt.text =
//            assignmentGrade?.grade.toString() + "%" // divided by total grade * 100
//        holder.viewBinding.progressPercentage.progress =
//            assignmentGrade?.grade?.toInt() ?: 0 // divided by total grade * 100
    }

    class AssignmentsGradesViewHolder(val viewBinding: ItemGradeBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {


    }

    fun changeData(assignmentsGrades: List<AssignmentResponseDTO>) {
        this.assignmentsGrades = assignmentsGrades
        notifyDataSetChanged()
    }


}