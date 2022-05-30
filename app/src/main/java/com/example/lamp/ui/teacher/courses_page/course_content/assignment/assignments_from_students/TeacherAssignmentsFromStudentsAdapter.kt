package com.example.lamp.ui.teacher.courses_page.course_content.assignment.assignments_from_students

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import aws.smithy.kotlin.runtime.http.Url
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherAssignmentsFromStudentsBinding
import com.example.lamp.ui.student.student_course_page.course_content.assignment.AssignmentFromStudentItem
import java.io.File
import java.net.URL

class TeacherAssignmentsFromStudentsAdapter(var assignmentList: MutableList<AssignmentFromStudentItem?>?) :
    RecyclerView.Adapter<TeacherAssignmentsFromStudentsAdapter.ViewHolder>() {
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
        holder.itemViewBinding.assignGrade.setOnClickListener {
            if (holder.itemViewBinding.studentGradeTxt.text.toString() == "" ||
                holder.itemViewBinding.studentGradeTxt.text.toString()
                    .toInt() > holder.itemViewBinding.points.text.toString().toInt()
            ) {
                holder.itemViewBinding.studentGradeTxt.error="please enter valid grade"
            } else {
                item?.studentGrade = holder.itemViewBinding.studentGradeTxt.text.toString().toInt()
                it.setBackgroundResource(R.color.green)
            }
        }

        holder.itemViewBinding.attachment.setOnClickListener {
            onPdfOpenListener?.onPdfOpen()
        }
    }
    var  onPdfOpenListener:OnPdfOpenListener?=null
    interface OnPdfOpenListener{
        fun onPdfOpen()
    }
    override fun getItemCount(): Int = assignmentList?.size ?: 0
}

