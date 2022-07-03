package com.example.lamp.ui.student.student_course_page.course_content.quiz.answers_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.QuestionChoiceResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemStudentCourseQuizQuestionAnswerBinding

class StudentQuizAnswersAdapter(var answers: MutableList<QuestionChoiceResponseDTO?>? = null) :
    RecyclerView.Adapter<StudentQuizAnswersAdapter.ViewHolder>() {
    var itemSelectedIndex = -1

    class ViewHolder(var viewBinding: ItemStudentCourseQuizQuestionAnswerBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun changeColor(resId: Int) {
            this.viewBinding.answerText.setBackgroundResource(resId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding: ItemStudentCourseQuizQuestionAnswerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_student_course_quiz_question_answer,
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = answers?.get(position)!!
        holder.viewBinding.item = item

        holder.viewBinding.answerText.setOnClickListener {
            if (itemSelectedIndex > -1) {
                holder.changeColor(R.color.green)
            }
            itemSelectedIndex = holder.absoluteAdapterPosition
            onAnswerSelectedListener?.onAnswerSelected(item)

        }

        if (itemSelectedIndex > -1) {
            if (position == itemSelectedIndex) {
                holder.changeColor(R.color.green)
            } else {
                holder.changeColor(com.workfort.linkpreview.R.color.md_light_blue_100)
//                notifyItemChanged(position)
            }
        }

    }

    var onAnswerSelectedListener: OnAnswerSelectedListener? = null

    interface OnAnswerSelectedListener {
        fun onAnswerSelected(answer: QuestionChoiceResponseDTO)
    }

    fun changeData(newAnswers: List<QuestionChoiceResponseDTO?>?) {
        answers = newAnswers?.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = answers?.size ?: 0

}