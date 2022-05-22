package com.example.lamp.ui.teacher.courses_page.course_content.quiz.questions_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherCourseQuizCreateQuestionBinding


class TeacherQuizQuestionsAdapter(var questions: MutableList<QuestionItem>? = null) :
    RecyclerView.Adapter<TeacherQuizQuestionsAdapter.ViewHolder>() {
    lateinit var viewBinding: ItemTeacherCourseQuizCreateQuestionBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_teacher_course_quiz_create_question,
            parent,
            false
        )
        return ViewHolder(viewBinding)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var questions = questions?.get(position)
        holder.viewBinding.item = questions

    }

    override fun getItemCount(): Int = questions?.size ?: 0

    class ViewHolder(val viewBinding: ItemTeacherCourseQuizCreateQuestionBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

    }
}