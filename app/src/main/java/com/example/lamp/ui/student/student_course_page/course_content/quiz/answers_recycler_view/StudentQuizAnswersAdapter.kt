package com.example.lamp.ui.student.student_course_page.course_content.quiz.answers_recycler_view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemStudentCourseQuizQuestionAnswerBinding
import com.example.lamp.databinding.ItemTeacherCourseQuizQuestionAnswerBinding
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.answers_recycler_view.AnswerItem

class StudentQuizAnswersAdapter(var answers: MutableList<AnswerItem>) :
    RecyclerView.Adapter<StudentQuizAnswersAdapter.ViewHolder>() {
    var itemSelectedIndex=-1
    class ViewHolder(var viewBinding: ItemStudentCourseQuizQuestionAnswerBinding) :
        RecyclerView.ViewHolder(viewBinding.root){
            fun changeColor(resId:Int){
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
        var item= answers[position]
        holder.viewBinding.item=item

        holder.viewBinding.answerText.setOnClickListener{
            itemSelectedIndex=position
            holder.changeColor(R.color.green)
            onAnswerSelectedListener?.onAnswerSelected(position)
        }

        if (itemSelectedIndex>-1){
            if (position==itemSelectedIndex){
                holder.changeColor(R.color.green)
            }
            else{
                holder.changeColor(com.workfort.linkpreview.R.color.md_light_blue_100)
            }
        }

    }
    var onAnswerSelectedListener:OnAnswerSelectedListener?=null
    interface OnAnswerSelectedListener{
        fun onAnswerSelected(position: Int)
    }
    fun changeData(newAnswers:MutableList<AnswerItem>){
        answers=newAnswers
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = answers?.size ?:0

}