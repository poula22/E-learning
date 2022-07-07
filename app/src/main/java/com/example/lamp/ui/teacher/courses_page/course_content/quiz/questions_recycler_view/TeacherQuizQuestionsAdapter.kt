package com.example.lamp.ui.teacher.courses_page.course_content.quiz.questions_recycler_view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.QuestionChoiceResponse
import com.example.domain.model.QuestionChoiceResponseDTO
import com.example.domain.model.QuestionResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherCourseQuizCreateQuestionBinding
import com.example.lamp.ui.student.student_course_page.course_content.quiz.quizzes_recycler_view.QuestionItem
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.answers_recycler_view.AnswerItem
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.answers_recycler_view.TeacherQuizAnswersAdapter
//var questions: MutableList<QuestionResponseDTO>? = null
//                                  ,var answersChoices:MutableList<QuestionChoiceResponseDTO>?=null

class TeacherQuizQuestionsAdapter(var questions: MutableList<QuestionItem>? = null) :
    RecyclerView.Adapter<TeacherQuizQuestionsAdapter.ViewHolder>() {
//    var answers: MutableList<AnswerItem> = mutableListOf()

    init {
        if (questions==null){
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
        holder.viewBinding.item=item?.question
        holder.viewBinding.createQuestionQuestionText.addTextChangedListener{
            Log.v("pos:::",item.toString())
        }

        val answerAdapter = TeacherQuizAnswersAdapter(item?.answers)
        val layoutManager = LinearLayoutManager(
            holder.viewBinding.createQuestionAnswerList.context,
            LinearLayoutManager.VERTICAL,
            false
        )

        layoutManager.initialPrefetchItemCount = item?.answers?.size ?:0

        holder.viewBinding.createQuestionAnswerList.layoutManager = layoutManager
        holder.viewBinding.createQuestionAnswerList.adapter = answerAdapter
        holder.viewBinding.createQuestionAnswerList.setRecycledViewPool(viewPool)
        holder.viewBinding.createQuestionAddAnswer.setOnClickListener{
            val answer=QuestionChoiceResponseDTO()
            if (item==null){
                item=QuestionItem()
            }
            if(item?.answers==null){
                item?.answers= mutableListOf()
            }
//            item?.answers?.add(AnswerItem(answer))
            answerAdapter.addAnswer(answer)
        }
        holder.viewBinding.createQuestionDeleteQuestion.setOnClickListener{
            removeQuestion(holder.absoluteAdapterPosition,item!!)
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
//        answerAdapter.notifyDataSetChanged()
    }

    fun changeAnswers(questionChoices: List<QuestionChoiceResponseDTO>?) {
//        answerAdapter.changeAnswers(questionChoices)
    }

    var onQuestionAddedListener:OnQuestionAddedListener?=null
    interface OnQuestionAddedListener{
        fun onQuestionAdded(question: QuestionResponseDTO)
    }

    class ViewHolder(val viewBinding: ItemTeacherCourseQuizCreateQuestionBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

}