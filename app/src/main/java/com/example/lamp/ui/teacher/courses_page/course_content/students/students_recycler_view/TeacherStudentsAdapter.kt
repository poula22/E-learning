package com.example.lamp.ui.teacher.courses_page.course_content.students.students_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.StudentResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.ItemTeacherStudentsBinding


class TeacherStudentsAdapter(var students: List<StudentResponseDTO>? = null) :
    RecyclerView.Adapter<TeacherStudentsAdapter.ViewHolder>() {

    lateinit var viewBinding: ItemTeacherStudentsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewBinding = DataBindingUtil.inflate<ItemTeacherStudentsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_teacher_students,
            parent,
            false
        )
        return ViewHolder(viewBinding)

    }

    fun setFilteredList(filteredList: MutableList<StudentResponseDTO>) {
        this.students = filteredList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var student = students?.get(position)
        holder.viewBinding.studentName.text = student?.firstName + " " + student?.lastName
        student?.let { holder.viewBinding.roundedImageView.setImageResource(R.drawable.student) }
        holder.viewBinding.studentEmail.text = student?.emailAddress
        holder.viewBinding.studentPhone.text = student?.phone
        holder.viewBinding.studentNameDetails.text = viewBinding.studentName.text
//////
        holder.expandCollapseView()
////////
    }

    override fun getItemCount(): Int = students?.size ?: 0

    class ViewHolder(val viewBinding: ItemTeacherStudentsBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun expandCollapseView() {
            viewBinding.detailsBtn.setOnClickListener {
                if (viewBinding.cardGroup.isVisible) {
//                TransitionManager.beginDelayedTransition(
//                    viewBinding.card,
//                    AutoTransition()
//                );
                    viewBinding.detailsBtn.text = "Show Details"
                    viewBinding.cardGroup.isVisible = false
//                arrow.setImageResource(android.R.drawable.arrow_down_float);
                } else {
//                TransitionManager.beginDelayedTransition(
//                    viewBinding.card,
//                    AutoTransition()
//                );
                    viewBinding.detailsBtn.text = "Hide Details"
                    viewBinding.cardGroup.isVisible = true
//                arrow.setImageResource(android.R.drawable.arrow_up_float);
                }
            }
        }
    }

    fun changeData(students: List<StudentResponseDTO>) {
        this.students = students
        notifyDataSetChanged()
    }

}