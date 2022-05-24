package com.example.lamp.ui.student.student_course_page.course_content.material.lessons_recycler_view.sections_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemStudentCourseMaterialLessonContentBinding
import com.example.lamp.ui.teacher.courses_page.course_content.material.lessons_recycler_view.SectionItem

class StudentCourseLessonsSectionsAdapter(var sectionsList: List<SectionItem?>?) :
    RecyclerView.Adapter<StudentCourseLessonsSectionsAdapter.ViewHolder>() {
    class ViewHolder(var viewBinding: ItemStudentCourseMaterialLessonContentBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewBinding: ItemStudentCourseMaterialLessonContentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_student_course_material_lesson_content,
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = sectionsList?.get(position)
        holder.viewBinding.item = item?.sectionName
    }

    override fun getItemCount(): Int = sectionsList?.size ?: 0
}