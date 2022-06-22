package com.example.lamp.ui.student.student_course_page.course_content.material.lessons_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.LessonResponse
import com.example.lamp.R
import com.example.lamp.databinding.ItemStudentCourseMaterialBinding
import com.example.lamp.ui.student.student_course_page.course_content.material.lessons_recycler_view.sections_recycler_view.StudentCourseLessonsSectionsAdapter
import com.example.lamp.ui.teacher.courses_page.course_content.material.lessons_recycler_view.LessonItem

class StudentCourseLessonsAdapter(var lessonList:List<LessonResponse?>):RecyclerView.Adapter<StudentCourseLessonsAdapter.ViewHolder>(){

    private val viewPool = RecyclerView.RecycledViewPool()
    class ViewHolder(var viewBinding: ItemStudentCourseMaterialBinding):RecyclerView.ViewHolder(viewBinding.root){
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
        var viewBinding:ItemStudentCourseMaterialBinding=
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_student_course_material,parent,false)
            return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item=lessonList?.get(position)
        holder.viewBinding.item=item
        holder.viewBinding.lessonTitle.setOnClickListener {
            onLessonClickListener?.onLessonClick(item?.id!!)
        }
//        val layoutManager = LinearLayoutManager(
//            holder.viewBinding.sectionRecyclerView.context,
//            LinearLayoutManager.VERTICAL,
//            false
//        )

//        layoutManager.initialPrefetchItemCount = item?.lessonSection?.size ?:0
//        val sectionAdapter = StudentCourseLessonsSectionsAdapter(
//            item?.lessonSection
//        )
//        holder.viewBinding.sectionRecyclerView.layoutManager = layoutManager
//        holder.viewBinding.sectionRecyclerView.adapter = sectionAdapter
//        holder.viewBinding.sectionRecyclerView.setRecycledViewPool(viewPool)
        holder.expandCollapseView()

    }
    fun updateLessonsList(lessonList:List<LessonResponse>){
        this.lessonList=lessonList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =lessonList?.size ?:0

    var onLessonClickListener:OnLessonClickListener?=null

    interface OnLessonClickListener{
        fun onLessonClick(lessonId:Int)
    }
}