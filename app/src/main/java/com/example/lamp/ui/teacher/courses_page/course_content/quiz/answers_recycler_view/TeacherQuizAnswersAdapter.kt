package com.example.lamp.ui.teacher.courses_page.course_content.quiz.answers_recycler_view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.QuestionChoiceResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherCourseQuizQuestionAnswerBinding

class TeacherQuizAnswersAdapter(var answers: MutableList<AnswerItem>?) :
    RecyclerView.Adapter<TeacherQuizAnswersAdapter.ViewHolder>() {
    init {
        if (answers==null){
            answers= mutableListOf()
        }
    }
    var selectedItem:Int=-1


    class ViewHolder(var viewBinding: ItemTeacherCourseQuizQuestionAnswerBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

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
        var item=answers?.get(position)
        holder.viewBinding.item=item
        holder.viewBinding.answerListItemAnswerText.addTextChangedListener {
            Log.v("pos:::",item.toString())
        }
        holder.viewBinding.answerListItemDelete.setOnClickListener {
            Log.v("pos:::", item!!.toString())
            removeItem(holder.absoluteAdapterPosition,item)
        }

        holder.viewBinding.answerListItemCorrect.setOnClickListener {
            if (selectedItem>-1){
//                var preItem=answers?.get(selectedItem)
//                preItem?.isCorrect=false
//                notifyItemChanged(selectedItem)
                notifyItemChanged(selectedItem)
            }
            selectedItem=holder.absoluteAdapterPosition
            notifyItemChanged(selectedItem)
        }

        item?.isCorrect= (selectedItem==position)

    }


    override fun getItemCount(): Int = answers?.size ?:0

    fun addAnswer(){
        answers?.add(AnswerItem())
        notifyItemInserted(answers?.size?.minus(1)!!)
    }


    fun changeAnswers(questionAnswer: List<QuestionChoiceResponseDTO>?) {
        answers?.clear()

        questionAnswer?.forEach {
            answers?.add(AnswerItem(it))
        }

        notifyDataSetChanged()
    }

    private fun removeItem(position: Int, item: AnswerItem?) {
        answers?.remove(item)
        if (selectedItem==position){
            selectedItem=-1
        }

        notifyItemRemoved(position)
    }

}