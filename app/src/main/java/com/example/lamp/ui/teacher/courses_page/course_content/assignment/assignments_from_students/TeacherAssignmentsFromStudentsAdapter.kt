package com.example.lamp.ui.teacher.courses_page.course_content.assignment.assignments_from_students

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.AssignmentAnswerDetailsResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherAssignmentsFromStudentsBinding

class TeacherAssignmentsFromStudentsAdapter(var assignmentList: List<AssignmentAnswerDetailsResponseDTO>?=null
    ,val totalPoints:Int) : RecyclerView.Adapter<TeacherAssignmentsFromStudentsAdapter.ViewHolder>() {
    class ViewHolder(var itemViewBinding: ItemTeacherAssignmentsFromStudentsBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewBinding: ItemTeacherAssignmentsFromStudentsBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_teacher_assignments_from_students,
                parent,
                false
            )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = assignmentList?.get(position)
        holder.itemViewBinding.item = item
        holder.itemViewBinding.points.setText(totalPoints)
        holder.itemViewBinding.assignGrade.setOnClickListener {
            if (holder.itemViewBinding.studentGradeTxt.text.toString() == "" ||
                holder.itemViewBinding.studentGradeTxt.text.toString()
                    .toInt() > holder.itemViewBinding.points.text.toString().toInt()
            ) {
                holder.itemViewBinding.studentGradeTxt.error="please enter valid grade"
            } else {
                onGradesSubmitListener?.let {  submitListener->
                    submitListener.onGradeSubmit(item!!,holder.itemViewBinding.studentGradeTxt.text.toString().toInt())
                    it.setBackgroundResource(R.color.green)
                }

            }
        }

        holder.itemViewBinding.attachment.setOnClickListener {
            onPdfOpenListener?.onPdfOpen(item?.pdf)
        }
    }

    override fun getItemCount(): Int = assignmentList?.size ?: 0
    fun changeData(assignmentList: List<AssignmentAnswerDetailsResponseDTO>) {
        this.assignmentList=assignmentList
    }

    var  onPdfOpenListener:OnPdfOpenListener?=null
    var onGradesSubmitListener:OnGradesSubmitListener?=null

    interface OnPdfOpenListener{
        fun onPdfOpen(pdf:String?)
    }
    interface OnGradesSubmitListener{
        fun onGradeSubmit(assignmentAnswerDetails: AssignmentAnswerDetailsResponseDTO, Grade: Int)
    }
}

