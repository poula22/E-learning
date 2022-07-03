package com.example.lamp.ui.teacher.courses_page.course_content.material.lessons_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.LessonResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherCourseMaterialBinding

class TeacherCourseLessonsAdapter(var lessonList:MutableList<LessonResponseDTO>?=null):RecyclerView.Adapter<TeacherCourseLessonsAdapter.ViewHolder>(){

    class ViewHolder(var viewBinding:ItemTeacherCourseMaterialBinding):RecyclerView.ViewHolder(viewBinding.root){
        fun expandCollapseView() {
            viewBinding.detailsBtn.setOnClickListener {
                if (viewBinding.cardGroup.isVisible) {
//                TransitionManager.beginDelayedTransition(
//                    viewBinding.card,
//                    AutoTransition()
//                );
                    viewBinding.detailsBtn.text = "Show Details"
                    viewBinding.cardGroup.isVisible = false
//                arrow.setImageResource(android.R.drawable.arrow_down_float);
                } else {
//                TransitionManager.beginDelayedTransition(
//                    viewBinding.card,
//                    AutoTransition()
//                );
                    viewBinding.detailsBtn.text = "Hide Details"
                    viewBinding.cardGroup.isVisible = true

//                arrow.setImageResource(android.R.drawable.arrow_up_float);
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewBinding:ItemTeacherCourseMaterialBinding=
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_teacher_course_material,parent,false)
            return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item=lessonList?.get(position)
        holder.viewBinding.item=item
        holder.expandCollapseView()
        holder.viewBinding.lessson.setOnClickListener {
            onItemClickListener?.onItemClick(item!!)
        }
    }

    override fun getItemCount(): Int =lessonList?.size ?:0

    var onItemClickListener:OnItemClickListener?=null
    fun changeData(lessonContent: List<LessonResponseDTO>?) {
        lessonList?.clear()
        lessonContent?.let {
            lessonList?.addAll(lessonContent)
            notifyDataSetChanged()
        }
    }


    interface OnItemClickListener{
        fun onItemClick(lesson: LessonResponseDTO)
    }


}