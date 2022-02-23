package com.example.lamp.ui.teacher.courses_page.courses_recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.recyclerviewpracticekotlin.CourseItem

class TeacherCoursesAdapter(var coursesItemsList : List<CourseItem>?=null,val type:Int): RecyclerView.Adapter<TeacherCoursesAdapter.CoursesItemViewHolder>() {
    class CoursesItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var courseName: TextView = itemView.findViewById<TextView>(R.id.courses_course_name)
        var courseCode: TextView = itemView.findViewById<TextView>(R.id.courses_code)
    }
    val HOME_SCREEN=R.layout.item_student_home_course_rv
    val COURSES_SCREEN=R.layout.item_student_courses

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesItemViewHolder {
        var screen=HOME_SCREEN
        if(type== 1){
            screen=COURSES_SCREEN
        }
       return CoursesItemViewHolder(LayoutInflater.from(parent.context).inflate(screen,parent,false))
    }

    override fun onBindViewHolder(holder: CoursesItemViewHolder, position: Int) {
        var course=coursesItemsList?.get(position)
        holder.courseName.text=course?.courseName
        holder.courseCode.text=course?.courseDescription
    }

    override fun getItemCount(): Int =coursesItemsList?.size ?:0
}