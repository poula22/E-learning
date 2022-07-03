package com.example.lamp.ui.teacher.courses_page.course_content.grades.student_grades_page.quizzes_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.QuizResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemGradeBinding

class QuizzesGradesAdapter(var quizzesGrades: MutableList<QuizResponseDTO>? = null) :
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
        holder.viewBinding.percentageTxt.text =
            quizGrade?.grade.toString() + "%"  // divided by total grade * 100
        holder.viewBinding.progressPercentage.progress =
            quizGrade?.grade!!.toInt()  // divided by total grade * 100
    }

    class QuizzesGradesViewHolder(val viewBinding: ItemGradeBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {


    }
}