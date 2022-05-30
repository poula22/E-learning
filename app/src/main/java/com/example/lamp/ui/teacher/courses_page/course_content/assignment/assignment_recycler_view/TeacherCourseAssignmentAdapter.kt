package com.example.lamp.ui.teacher.courses_page.course_content.assignment.assignment_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherCourseAssignmentBinding
import com.example.lamp.ui.student.student_course_page.course_content.assignment.AssignmentItem

class TeacherCourseAssignmentAdapter(var assignmentList: MutableList<String>) :
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
        var item = assignmentList.get(position)
        holder.itemViewBinding.item= item
        holder.itemViewBinding.assignmentTxt.setOnClickListener {
                onAssignmentClickListener?.setOnAssignmentClickListener(position)
        }

    }

    override fun getItemCount(): Int = assignmentList.size ?: 0

    var onAssignmentClickListener: OnAssignmentClickListener? = null

    interface OnAssignmentClickListener {
        fun setOnAssignmentClickListener(pos:Int)
    }

}