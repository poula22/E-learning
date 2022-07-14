package com.example.lamp.ui.student.student_course_page.course_content.grades.quizzes_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.QuizResponseDTO
import com.example.domain.model.TeacherQuizResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemGradeBinding

class QuizzesGradesAdapter(var quizzesGrades: List<QuizResponseDTO>? = null) :
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
        if (quizGrade?.grade != null) {
            holder.viewBinding.percentageTxt.text = quizGrade.grade.toString() + "%"
            holder.viewBinding.progressPercentage.progress =
                quizGrade.grade!!.toInt() //TODO: divide by todo * 100
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

    fun changeData(quizzesGrades: List<QuizResponseDTO>) {
        this.quizzesGrades = quizzesGrades
        notifyDataSetChanged()
    }


}