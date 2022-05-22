package com.example.lamp.ui.teacher.courses_page.course_content.quiz.quizzes_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherCourseQuizCardBinding

class TeacherQuizAdapter(var quizzes: MutableList<TeacherQuizItem>? = null) :
    RecyclerView.Adapter<TeacherQuizAdapter.ViewHolder>() {
    lateinit var viewBinding: ItemTeacherCourseQuizCardBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_teacher_course_quiz_card,
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var quizzes = quizzes?.get(position)
        holder.viewBinding.item = quizzes

    }

    override fun getItemCount(): Int = quizzes?.size ?: 0

    class ViewHolder(val viewBinding: ItemTeacherCourseQuizCardBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

    }
}