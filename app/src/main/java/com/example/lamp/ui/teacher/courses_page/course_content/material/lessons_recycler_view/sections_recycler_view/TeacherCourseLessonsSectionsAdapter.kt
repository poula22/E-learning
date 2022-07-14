package com.example.lamp.ui.teacher.courses_page.course_content.material.lessons_recycler_view.sections_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherCourseMaterialLessonContentBinding
import com.example.lamp.ui.teacher.courses_page.course_content.material.lessons_recycler_view.SectionItem

class TeacherCourseLessonsSectionsAdapter(var sectionsList: List<SectionItem?>?) :
    RecyclerView.Adapter<TeacherCourseLessonsSectionsAdapter.ViewHolder>() {
    class ViewHolder(var viewBinding: ItemTeacherCourseMaterialLessonContentBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewBinding: ItemTeacherCourseMaterialLessonContentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_teacher_course_material_lesson_content,
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = sectionsList?.get(position)
        holder.viewBinding.item = item?.sectionName
        holder.viewBinding.editSection.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = sectionsList?.size ?: 0
    

}