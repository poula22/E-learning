package com.example.lamp.ui.teacher.courses_page.course_content.assignment.assignments_from_students

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherAssignmentsFromStudentsBinding
import com.example.lamp.ui.student.student_course_page.course_content.assignment.AssignmentFromStudentItem
import com.rajat.pdfviewer.PdfViewerActivity

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
            if (holder.itemViewBinding.studentGrade.text.toString() == "" ||
                holder.itemViewBinding.studentGrade.text.toString()
                    .toInt() > holder.itemViewBinding.points.text.toString().toInt()
            ) {
                Toast.makeText(it.context, "Please, enter a valid grade", Toast.LENGTH_SHORT).show()
            } else {
                item?.studentGrade = holder.itemViewBinding.studentGrade.text.toString().toInt()
                it.background = Color.GREEN.toDrawable()
            }

        }
        holder.itemViewBinding.attachment.setOnClickListener {
            it.context.startActivity(
                // Use 'launchPdfFromPath' if you want to use assets file (enable "fromAssets" flag) / internal directory
                PdfViewerActivity.launchPdfFromUrl(
                    it.context,
                    "C:\\Users\\Pola\\Desktop\\11111.pdf",                                // PDF URL in String format
                    "11111.pdf", // PDF Name/Title in String format
                    "", // Enable/Disable "fromAssets" flag
                    true, // Enable/Disable "fromSD" flag
                )
            )

        }
    }

    override fun getItemCount(): Int = assignmentList?.size ?: 0
}

