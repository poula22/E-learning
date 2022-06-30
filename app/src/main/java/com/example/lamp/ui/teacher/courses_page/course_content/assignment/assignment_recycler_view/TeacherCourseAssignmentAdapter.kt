package com.example.lamp.ui.teacher.courses_page.course_content.assignment.assignment_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.AssignmentDetailsResponse
import com.example.data.model.AssignmentResponse
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherCourseAssignmentBinding
import com.example.lamp.ui.student.student_course_page.course_content.assignment.AssignmentItem

class TeacherCourseAssignmentAdapter(var assignmentList: List<AssignmentResponse>?=null) :
    RecyclerView.Adapter<TeacherCourseAssignmentAdapter.ViewHolder>() {
    class ViewHolder(var itemViewBinding: ItemTeacherCourseAssignmentBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewBinding: ItemTeacherCourseAssignmentBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_teacher_course_assignment,
                parent,
                false
            )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = assignmentList?.get(position)
        holder.itemViewBinding.item= item
        holder.itemViewBinding.assignmentTxt.setOnClickListener {
            assignmentList?.let { it1 -> onAssignmentClickListener?.setOnAssignmentClickListener(it1.get(position)) }
        }

    }

    override fun getItemCount(): Int = assignmentList?.size ?: 0
    fun changeData(list: List<AssignmentResponse>?) {
        if (list != null) {
            assignmentList=list
            notifyDataSetChanged()
        }
    }

    var onAssignmentClickListener: OnAssignmentClickListener? = null

    interface OnAssignmentClickListener {
        fun setOnAssignmentClickListener(assignment:AssignmentResponse)
    }

}