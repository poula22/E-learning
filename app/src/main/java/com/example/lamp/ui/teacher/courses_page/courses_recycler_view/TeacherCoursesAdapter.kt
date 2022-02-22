package com.example.lamp.ui.teacher.courses_page.courses_recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.recyclerviewpracticekotlin.CourseItem

class TeacherCoursesAdapter(var coursesItemsList : List<CourseItem>?=null): RecyclerView.Adapter<TeacherCoursesAdapter.CoursesItemViewHolder>() {
    class CoursesItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var courseName: TextView = itemView.findViewById<TextView>(R.id.course_name)
        var courseCode: TextView = itemView.findViewById<TextView>(R.id.courses_code)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesItemViewHolder {
       return CoursesItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_student_courses,parent,false))
    }

    override fun onBindViewHolder(holder: CoursesItemViewHolder, position: Int) {
        var course=coursesItemsList?.get(position)
        holder.courseName.text=course?.courseName
        holder.courseCode.text=course?.courseDescription
    }

    override fun getItemCount(): Int =coursesItemsList?.size ?:0
}