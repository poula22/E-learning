package com.example.lamp.ui.student.student_course_page.course_content.grades.assignments_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.AssignmentDetailsResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemGradeBinding

class AssignmentsGradesAdapter(var assignmentsGrades: List<AssignmentDetailsResponseDTO>? = null) :
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
        if (assignmentGrade?.assignedGrade != null) {
            holder.viewBinding.percentageTxt.text = assignmentGrade.assignedGrade.toString() + "%"
            holder.viewBinding.progressPercentage.progress =
                assignmentGrade.assignedGrade!!.toInt() //TODO: divide by todo * 100
            holder.viewBinding.notGraded.isVisible = false
        } else {
            holder.viewBinding.notGraded.isVisible = true
            holder.viewBinding.percentageTxt.isVisible = false
            holder.viewBinding.progressPercentage.isVisible = false
        }
    }

    class AssignmentsGradesViewHolder(val viewBinding: ItemGradeBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {


    }

    fun changeData(assignmentsGrades: List<AssignmentDetailsResponseDTO>) {
        this.assignmentsGrades = assignmentsGrades
        notifyDataSetChanged()
    }


}