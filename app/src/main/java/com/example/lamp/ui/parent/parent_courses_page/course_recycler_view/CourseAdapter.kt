package com.example.lamp.ui.parent.parent_courses_page.course_recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemParentCourseBinding

class CourseAdapter(var courses : List<ParentCourseItem>?=null): RecyclerView.Adapter<CourseAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemParentCourseBinding:ItemParentCourseBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.item_parent_course,parent,false)
        return ViewHolder(itemParentCourseBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = courses?.get(position)
        holder.itemParentCourseBinding.teacherName.text = course?.teacherName
        holder.itemParentCourseBinding.childName.text = course?.childName
        holder.itemParentCourseBinding.courseName.text=course?.courseName
    }

    override fun getItemCount(): Int {
        return courses?.size ?:0;
    }


    class ViewHolder(val itemParentCourseBinding: ItemParentCourseBinding) : RecyclerView.ViewHolder(itemParentCourseBinding.root){
        //continue
    }

}