package com.example.lamp.ui.teacher.courses_page.course_content.grades.student_grades_page.quizzes_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.NewQuizResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemGradeBinding

class QuizzesGradesAdapter(var quizzesGrades: List<NewQuizResponseDTO>? = null) :
    RecyclerView.Adapter<QuizzesGradesAdapter.QuizzesGradesViewHolder>() {

    lateinit var viewBinding: ItemGradeBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuizzesGradesViewHolder {
        viewBinding = DataBindingUtil.inflate<ItemGradeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_grade,
            parent,
            false
        )
        return QuizzesGradesViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return quizzesGrades?.size ?: 0
    }

    override fun onBindViewHolder(holder: QuizzesGradesViewHolder, position: Int) {
        var quizGrade = quizzesGrades?.get(position)
        holder.viewBinding.title.text = quizGrade?.title.toString()
        if (quizGrade?.assignedGrade != null) {
            holder.viewBinding.percentageTxt.text =
                quizGrade.assignedGrade!!.div(quizGrade.totalPoints!!).times(100).toString()
            holder.viewBinding.progressPercentage.progress =
                quizGrade.assignedGrade!!.div(quizGrade.totalPoints!!).times(100).toString().toInt()
            holder.viewBinding.notGraded.isVisible = false
        } else {
            holder.viewBinding.notGraded.isVisible = true
            holder.viewBinding.percentageTxt.isVisible = false
            holder.viewBinding.progressPercentage.isVisible = false
        }
    }

    class QuizzesGradesViewHolder(val viewBinding: ItemGradeBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {


    }

    fun changeData(quizzesGrades: List<NewQuizResponseDTO>) {
        this.quizzesGrades = quizzesGrades
        notifyDataSetChanged()
    }


}