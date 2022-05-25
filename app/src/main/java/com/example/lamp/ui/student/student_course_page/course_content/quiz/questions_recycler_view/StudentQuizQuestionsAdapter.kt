package com.example.lamp.ui.student.student_course_page.course_content.quiz.questions_recycler_view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherCourseQuizCreateQuestionBinding
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.answers_recycler_view.TeacherQuizAnswersAdapter
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.questions_recycler_view.QuestionItem


open class StudentQuizQuestionsAdapter(var questions: MutableList<QuestionItem>? = null) :
    RecyclerView.Adapter<StudentQuizQuestionsAdapter.ViewHolder>() {
    init {
        if ( questions==null){
            questions= mutableListOf()
        }
    }
    private val viewPool = RecyclerView.RecycledViewPool()
    private var counter=0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewBinding: ItemTeacherCourseQuizCreateQuestionBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_teacher_course_quiz_create_question,
            parent,
            false
        )
        return ViewHolder(viewBinding)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item=questions?.get(position)
        holder.viewBinding.item=item
        var answers=item?.answers
        holder.viewBinding.createQuestionQuestionText.addTextChangedListener{
            Log.v("pos:::",item.toString())
        }
        val layoutManager = LinearLayoutManager(
            holder.viewBinding.createQuestionAnswerList.context,
            LinearLayoutManager.VERTICAL,
            false
        )

        layoutManager.initialPrefetchItemCount = item?.answers?.size ?:0
        val answerAdapter = TeacherQuizAnswersAdapter(
            answers
        )
        holder.viewBinding.createQuestionAnswerList.layoutManager = layoutManager
        holder.viewBinding.createQuestionAnswerList.adapter = answerAdapter
        holder.viewBinding.createQuestionAnswerList.setRecycledViewPool(viewPool)
        holder.viewBinding.createQuestionAddAnswer.setOnClickListener{
            answerAdapter.addAnswer()

        }
        holder.viewBinding.createQuestionDeleteQuestion.setOnClickListener{
            removeQuestion(position,item!!)
        }
    }

    override fun getItemCount(): Int = questions?.size ?: 0

    fun addQuestion(question: QuestionItem) {
        questions?.add(question)
        notifyItemInserted(questions?.size?.minus(1)!!)
    }
    private fun removeQuestion(position: Int, item: QuestionItem) {
        questions?.remove(item)
        notifyItemRemoved(position)
    }
    var onQuestionAddedListener:OnQuestionAddedListener?=null
    interface OnQuestionAddedListener{
        fun onQuestionAdded(question: QuestionItem)
    }

    class ViewHolder(val viewBinding: ItemTeacherCourseQuizCreateQuestionBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

}