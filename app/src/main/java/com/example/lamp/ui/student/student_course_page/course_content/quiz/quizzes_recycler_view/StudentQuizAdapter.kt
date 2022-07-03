package com.example.lamp.ui.student.student_course_page.course_content.quiz.quizzes_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.QuizResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemStudentCourseQuizCardBinding

class StudentQuizAdapter(var quizzes:List<QuizResponseDTO>? = null) :
    RecyclerView.Adapter<StudentQuizAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewBinding: ItemStudentCourseQuizCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_student_course_quiz_card,
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var quiz = quizzes?.get(position)
        holder.viewBinding.item = quiz
        holder.viewBinding.startExamBtn.setOnClickListener{
            onStartExamListener?.onStartExam(quiz?.id!!)
        }

    }
    var onStartExamListener:OnStartExamListener?=null

    interface OnStartExamListener{
        fun onStartExam(quizId:Int)
    }

    override fun getItemCount(): Int = quizzes?.size ?: 0
    fun changeData(it: List<QuizResponseDTO>?) {
        quizzes = it
        notifyDataSetChanged()
    }

    class ViewHolder(val viewBinding: ItemStudentCourseQuizCardBinding) :
        RecyclerView.ViewHolder(viewBinding.root)
}