package com.example.lamp.ui.parent.parent_home_page.courses_page.course_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.AssignmentResponseDTO
import com.example.domain.model.ParentChildCoursesResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemParentCourseBinding

class CourseAdapter(var courses: List<ParentChildCoursesResponseDTO>? = null) :
    RecyclerView.Adapter<CourseAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemParentCourseBinding: ItemParentCourseBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_parent_course,
            parent,
            false
        )
        return ViewHolder(itemParentCourseBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = courses?.get(position)
        holder.itemParentCourseBinding.teacherName.text =
            course?.teacherFirstName + " " + course?.teacherLastName
        holder.itemParentCourseBinding.childName.text
        holder.itemParentCourseBinding.courseName.text = course?.courseName
        holder.itemParentCourseBinding.card.setOnClickListener {
            onCourseClickListener?.setOnCourseClickListener(course!!)
        }
    }

    override fun getItemCount(): Int {
        return courses?.size ?: 0;
    }


    class ViewHolder(val itemParentCourseBinding: ItemParentCourseBinding) :
        RecyclerView.ViewHolder(itemParentCourseBinding.root) {
        //continue
    }


    fun changeData(courses: List<ParentChildCoursesResponseDTO>) {
        this.courses = courses
        notifyDataSetChanged()
    }

    var onCourseClickListener: OnCourseClickListener? = null

    interface OnCourseClickListener {
        fun setOnCourseClickListener(course: ParentChildCoursesResponseDTO)
    }




}