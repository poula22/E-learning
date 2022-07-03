package com.example.lamp.ui.teacher.courses_page.course_content.settings

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherCourseQuizQuestionAnswerBinding

class TestAdapter(var answers: HashMap<String,Boolean>?) :
    RecyclerView.Adapter<TestAdapter.ViewHolder>() {
//    var counter = 0
    private var backUp:String=""
    init {
        if (answers==null){
            answers= hashMapOf()
        }
    }


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
//        if (answers?.keys?.size?.minus(1)!! >=position){
//            holder.viewBinding.answerListItemAnswerText.setText(
//                answers?.keys?.elementAt(position)
//            )
//        }

        holder.viewBinding.answerListItemDelete.setOnClickListener {
            removeItem(holder.viewBinding.answerListItemAnswerText.text.toString())
        }
        holder.viewBinding.answerListItemAnswerText.setOnFocusChangeListener { v, hasFocus ->
            backUp=holder.viewBinding.answerListItemAnswerText.text.toString()
            if (hasFocus==false){
                backUp=""
                answers?.remove("default")
                addItem(holder.viewBinding.answerListItemAnswerText.text.toString())
                Log.v("action:::",holder.viewBinding.answerListItemAnswerText.text.toString())
                Log.v("size::::",answers?.size.toString())
            }
        }

        holder.viewBinding.answerListItemCorrect.setOnCheckedChangeListener { _, isChecked ->
            answers!![holder.viewBinding.answerListItemAnswerText.text.toString()] =
                isChecked
        }
    }

    override fun getItemCount(): Int = answers?.size ?:0

    fun addItem(answer: String) {
//        if (answers!!.containsKey(answer)==false){
//            counter++
//        }
        if (answers?.containsKey(answer) == false){
            answers!![answer] = false
        }

    }
    fun addAnswer(){
        answers?.put("default",false)
        notifyDataSetChanged()
    }


    fun removeItem(answer: String) {
        if (answers?.size != 0) {
            answers!!.remove(answer)
            notifyDataSetChanged()
        }
    }
}