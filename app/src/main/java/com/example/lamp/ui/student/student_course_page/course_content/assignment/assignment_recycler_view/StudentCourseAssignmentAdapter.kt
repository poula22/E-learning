package com.example.lamp.ui.student.student_course_page.course_content.assignment.assignment_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.AssignmentDetailsResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemStudentCourseAssignmentBinding
import com.example.lamp.ui.student.student_course_page.course_content.assignment.AssignmentItem


class StudentCourseAssignmentAdapter(var assignments: MutableList<AssignmentDetailsResponseDTO>? = null) :
    RecyclerView.Adapter<StudentCourseAssignmentAdapter.ViewHolder>() {

    lateinit var viewBinding: ItemStudentCourseAssignmentBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_student_course_assignment,
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    fun setFilteredList(filteredList: MutableList<AssignmentDetailsResponseDTO>) {
        this.assignments = filteredList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = assignments?.get(position)
        holder.viewBinding.item = item
        if (position%2==0){
            holder.viewBinding.itemAssignmentConstrainLayout.setBackgroundResource(R.color.dark_green)
        }else{
            holder.viewBinding.itemAssignmentConstrainLayout.setBackgroundResource(R.color.light_pink)
        }
        holder.viewBinding.itemAssignmentConstrainLayout.setOnClickListener {
            onStudentAssignmentClickedListener?.onAssignmentClick(holder.bindingAdapterPosition)
        }
    }
    var onStudentAssignmentClickedListener:OnStudentAssignmentClickedListener?=null
    interface OnStudentAssignmentClickedListener{
        fun onAssignmentClick(postion:Int)
    }

    override fun getItemCount(): Int = assignments?.size ?: 0

    class ViewHolder(val viewBinding: ItemStudentCourseAssignmentBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

    }

}




