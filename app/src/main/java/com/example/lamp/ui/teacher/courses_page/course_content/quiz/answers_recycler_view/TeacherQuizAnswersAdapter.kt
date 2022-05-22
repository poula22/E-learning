package com.example.lamp.ui.teacher.courses_page.course_content.quiz.answers_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherCourseQuizQuestionAnswerBinding
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.questions_recycler_view.QuestionItem

class TeacherQuizAnswersAdapter(var question: QuestionItem) :
    RecyclerView.Adapter<TeacherQuizAnswersAdapter.ViewHolder>() {
    var counter = 1

    init {
        question.answers = HashMap()
    }

    class ViewHolder(var viewBinding: ItemTeacherCourseQuizQuestionAnswerBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding: ItemTeacherCourseQuizQuestionAnswerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_teacher_course_quiz_question_answer,
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewBinding.answerListItemAnswerText.setText(
            question.answers?.keys?.elementAt(
                position
            )
        )
        holder.viewBinding.answerListItemDelete.setOnClickListener {
            removeItem(holder.viewBinding.answerListItemAnswerText.text.toString())
        }
        holder.viewBinding.answerListItemAnswerText.doAfterTextChanged {
            addItem(holder.viewBinding.answerListItemAnswerText.text.toString())
        }
        holder.viewBinding.answerListItemCorrect.setOnCheckedChangeListener { _, isChecked ->
            question.answers!![holder.viewBinding.answerListItemAnswerText.text.toString()] =
                isChecked
        }
    }

    override fun getItemCount(): Int = counter

    fun addItem(answer: String) {
        question.answers!![answer] = false
        counter++
        notifyDataSetChanged()
    }

    fun removeItem(answer: String) {
        if (counter != 0) {
            counter--
            question.answers!!.remove(answer)
            notifyDataSetChanged()
        }
    }
}