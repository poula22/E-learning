package com.example.lamp.ui.teacher.courses_page.course_content.homework.homework_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherCourseHomeWorkBinding

class TeacherCourseHomeworkAdapter(var homeworkList:MutableList<String?>?):RecyclerView.Adapter<TeacherCourseHomeworkAdapter.ViewHolder>(){
    class ViewHolder(var itemViewBinding:ItemTeacherCourseHomeWorkBinding):RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var viewBinding:ItemTeacherCourseHomeWorkBinding=DataBindingUtil
           .inflate(LayoutInflater.from(parent.context), R.layout.item_teacher_course_home_work,parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item=homeworkList?.get(position)
        holder.itemViewBinding.item=item
    }

    override fun getItemCount(): Int =homeworkList?.size ?:0
}