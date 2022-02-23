package com.example.lamp.ui.parent.parent_courses_page.course_recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
class CourseAdapter(var courses : List<ParentCourseItem>?=null): RecyclerView.Adapter<CourseAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_parent_course,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = courses?.get(position)
        holder.teacherName.text = course?.teacherName
        holder.childName.text = course?.childName
        holder.courseName.text=course?.courseName
    }

    override fun getItemCount(): Int {
        return courses?.size ?:0;
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var childName: TextView = itemView.findViewById<TextView>(R.id.child_name)
        var courseName: TextView = itemView.findViewById<TextView>(R.id.course_name)
        var teacherName: TextView = itemView.findViewById<TextView>(R.id.teacher_name)
        //continue
    }

}