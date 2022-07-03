package com.example.lamp.ui.teacher.courses_page.course_content.grades.student_grades_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.domain.model.AssignmentResponseDTO
import com.example.domain.model.QuizResponseDTO
import com.example.lamp.databinding.FragmentTeacherCourseGradesStudentContentBinding
import com.example.lamp.ui.teacher.courses_page.course_content.grades.student_grades_page.assignments_rv.AssignmentsGradesAdapter
import com.example.lamp.ui.teacher.courses_page.course_content.grades.student_grades_page.quizzes_rv.QuizzesGradesAdapter

class TeacherCourseGradesFragment(
    var assignmentsList: MutableList<AssignmentResponseDTO>? = null,
    var quizzesList: MutableList<QuizResponseDTO>? = null
) : Fragment() {

    lateinit var viewBinding: FragmentTeacherCourseGradesStudentContentBinding
    lateinit var assignmentsGradesAdapter: AssignmentsGradesAdapter
    lateinit var quizzesGradesAdapter: QuizzesGradesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            com.example.lamp.R.layout.fragment_teacher_course_grades,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews() {
        assignmentsGradesAdapter = AssignmentsGradesAdapter(assignmentsList)
        quizzesGradesAdapter = QuizzesGradesAdapter(quizzesList)
        viewBinding.assignmentsRv.adapter = assignmentsGradesAdapter
        viewBinding.quizzesRv.adapter = quizzesGradesAdapter

        // overall grades equals sum of assignments and quizzes grades divided by items count
        val overallGrades =
            (assignmentsList?.sumOf { it.grade!! } ?: 0) + (quizzesList?.sumOf { it.grade!! } ?: 0)
        val itemsCount = (assignmentsList?.size ?: 0) + (quizzesList?.size ?: 0)
        var overall = overallGrades / itemsCount
        viewBinding.overallPercentageTxt.text = overall.toString()


    }
}