package com.example.lamp.ui.teacher.courses_page.course_content.quiz.quizzes_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherCourseQuizCardBinding

class TeacherQuizAdapter(var quizzes: MutableList<QuizItem>? = null) :
    RecyclerView.Adapter<TeacherQuizAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewBinding: ItemTeacherCourseQuizCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_teacher_course_quiz_card,
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var quiz = quizzes?.get(position)
        holder.viewBinding.item = quiz
        holder.viewBinding.createQuizDeleteQuiz.setOnClickListener{
            quizzes?.remove(quiz)
//            notifyDataSetChanged()
        }
        holder.viewBinding.createQuizEditQuiz.setOnClickListener{
            onEditQuizListener?.onEditQuiz(quiz!!)
        }

    }
    var onEditQuizListener:OnEditQuizListener?=null

    interface OnEditQuizListener{
        fun onEditQuiz(quiz:QuizItem)
    }

    override fun getItemCount(): Int = quizzes?.size ?: 0

    class ViewHolder(val viewBinding: ItemTeacherCourseQuizCardBinding) :
        RecyclerView.ViewHolder(viewBinding.root)
}