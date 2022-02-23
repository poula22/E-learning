package com.example.lamp.ui.teacher.students_page.students_recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R


class TeacherStudentsAdapter(var students:MutableList<StudentItem>?=null):
    RecyclerView.Adapter<TeacherStudentsAdapter.ViewHolder> () {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var studentName: TextView = view.findViewById(R.id.student_name)
        var imageView: ImageView = view.findViewById(R.id.roundedImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_teacher_students, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var student = students?.get(position)
        holder.studentName.text = student?.studentName
        student?.let { holder.imageView.setImageResource(student.studentImageId) }
    }

    override fun getItemCount(): Int = students?.size ?: 0

}