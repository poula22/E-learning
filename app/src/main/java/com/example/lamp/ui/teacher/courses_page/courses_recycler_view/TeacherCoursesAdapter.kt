package com.example.lamp.ui.teacher.courses_page.courses_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.CourseResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemStudentCoursesBinding
import com.example.lamp.databinding.ItemStudentHomeCourseRvBinding
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CourseItem


class TeacherCoursesAdapter(var coursesItemsList: List<CourseResponseDTO>? = null, val type: Int) :
    RecyclerView.Adapter<TeacherCoursesAdapter.CoursesItemViewHolder>() {

    val HOME_SCREEN = R.layout.item_student_home_course_rv
    val COURSES_SCREEN = R.layout.item_student_courses
    lateinit var viewBinding: ViewDataBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesItemViewHolder {

        if (type == 0) {
            var screen = HOME_SCREEN
            viewBinding = DataBindingUtil.inflate<ItemStudentHomeCourseRvBinding>(
                LayoutInflater.from(parent.context),
                screen,
                parent,
                false
            )

        } else if (type == 1) {
            var screen = COURSES_SCREEN
            viewBinding = DataBindingUtil.inflate<ItemStudentCoursesBinding>(
                LayoutInflater.from(parent.context), screen, parent, false
            )
        }

        return CoursesItemViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: CoursesItemViewHolder, position: Int) {

        val item = coursesItemsList?.get(position)
        if (type == 0) {
            val view: ItemStudentHomeCourseRvBinding =
                holder.viewDataBinding as ItemStudentHomeCourseRvBinding
            view.item = item
            if(onCourseClickListener!=null){
                view.card.setOnClickListener{
                    onCourseClickListener!!.setOnCourseClickListener(item)
                }
            }
        } else if (type == 1) {
            val view: ItemStudentCoursesBinding =
                holder.viewDataBinding as ItemStudentCoursesBinding
            view.item = item
            if(onCourseClickListener!=null){
                view.card.setOnClickListener{
                    onCourseClickListener!!.setOnCourseClickListener(item)
                }
            }
        }
    }

    override fun getItemCount(): Int = coursesItemsList?.size ?: 0

    class CoursesItemViewHolder(var viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    var onCourseClickListener:OnCourseClickListener?=null
    interface OnCourseClickListener{
        fun setOnCourseClickListener(item: CourseResponseDTO?)
    }

}

