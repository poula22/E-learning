package com.example.lamp.ui.teacher.courses_page.course_content.quiz.questions_recycler_view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.QuestionChoiceResponseDTO
import com.example.domain.model.QuestionResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherCourseQuizCreateQuestionBinding
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.answers_recycler_view.AnswerItem
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.answers_recycler_view.TeacherQuizAnswersAdapter


class TeacherQuizQuestionsAdapter(var questions: MutableList<QuestionResponseDTO>? = null
                                  ,var answersChoices:MutableList<QuestionChoiceResponseDTO>?=null) :
    RecyclerView.Adapter<TeacherQuizQuestionsAdapter.ViewHolder>() {
    var answers: MutableList<AnswerItem> = mutableListOf()

    init {
        answersChoices?.forEach {
            answers.add(AnswerItem(it))
        }
    }
    val answerAdapter = TeacherQuizAnswersAdapter(
        answers
    )

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
        holder.viewBinding.createQuestionQuestionText.addTextChangedListener{
            Log.v("pos:::",item.toString())
        }
        val layoutManager = LinearLayoutManager(
            holder.viewBinding.createQuestionAnswerList.context,
            LinearLayoutManager.VERTICAL,
            false
        )

        layoutManager.initialPrefetchItemCount = answersChoices?.size ?:0

        holder.viewBinding.createQuestionAnswerList.layoutManager = layoutManager
        holder.viewBinding.createQuestionAnswerList.adapter = answerAdapter
        holder.viewBinding.createQuestionAnswerList.setRecycledViewPool(viewPool)
        holder.viewBinding.createQuestionAddAnswer.setOnClickListener{
            answerAdapter.addAnswer()

        }
        holder.viewBinding.createQuestionDeleteQuestion.setOnClickListener{
            removeQuestion(holder.absoluteAdapterPosition,item!!)
        }
    }

    override fun getItemCount(): Int = questions?.size ?: 0

    fun addQuestion(question: QuestionResponseDTO) {
        questions?.add(question)
        notifyItemInserted(questions?.size?.minus(1)!!)
    }
    private fun removeQuestion(position: Int, item: QuestionResponseDTO) {
        questions?.remove(item)
        notifyItemRemoved(position)
    }

    fun changeAnswers(questionChoices: List<QuestionChoiceResponseDTO>?) {
        answerAdapter.changeAnswers(questionChoices)
    }

    var onQuestionAddedListener:OnQuestionAddedListener?=null
    interface OnQuestionAddedListener{
        fun onQuestionAdded(question: QuestionResponseDTO)
    }

    class ViewHolder(val viewBinding: ItemTeacherCourseQuizCreateQuestionBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

}