package com.example.lamp.ui.student.student_course_page.course_content.grades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.AssignmentResponseDTO
import com.example.domain.model.QuizResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCourseGradesContentBinding
import com.example.lamp.ui.student.student_course_page.course_content.grades.assignments_rv.AssignmentsGradesAdapter
import com.example.lamp.ui.student.student_course_page.course_content.grades.quizzes_rv.QuizzesGradesAdapter

class StudentCourseGradesFragment(
    var assignmentsList: MutableList<AssignmentResponseDTO>? = null,
    var quizzesList: MutableList<QuizResponseDTO>? = null
) : Fragment() {

    lateinit var viewBinding: FragmentStudentCourseGradesContentBinding
    lateinit var assignmentsGradesAdapter: AssignmentsGradesAdapter
    lateinit var quizzesGradesAdapter: QuizzesGradesAdapter
    lateinit var viewModel: StudentCourseGradesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StudentCourseGradesViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_student_course_grades_content,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initViews()
        viewModel.getGradesByStudentId()
    }

    private fun subscribeToLiveData() {
        viewModel.assignmentsLiveData.observe(viewLifecycleOwner) {
            assignmentsGradesAdapter.changeData(it)
        }

        viewModel.quizzesLiveData.observe(viewLifecycleOwner) {
            quizzesGradesAdapter.changeData(it)
        }
    }

    var assignmentCount = 0
    var quizCount = 0
    var counter = 0
    fun initViews() {
        assignmentsGradesAdapter = AssignmentsGradesAdapter()
        quizzesGradesAdapter = QuizzesGradesAdapter()
        viewBinding.assignmentsRv.adapter = assignmentsGradesAdapter
        viewBinding.quizzesRv.adapter = quizzesGradesAdapter

//         overall grades equals sum of assignments and quizzes grades divided by items count
        val overallGrades =
            (assignmentsGradesAdapter.assignmentsGrades?.sumOf {
                it.assignedGrade?.div(it.totalPoints!!)?.times(100)!!
            }
                    )?.plus((quizzesGradesAdapter.quizzesGrades?.sumOf {
                    it.assignedGrade?.div(it.totalPoints!!)?.times(100)!!
                }!!))
        val itemsCount = (assignmentsGradesAdapter.assignmentsGrades?.size
                )?.plus((quizzesGradesAdapter.quizzesGrades?.size!!))
        if (itemsCount == 0 || overallGrades == null) {
            viewBinding.overallPercentageProgress.visibility = View.GONE
            viewBinding.overallPercentageTxt.text = "No grades"
        } else {
            var overall = overallGrades.div(itemsCount!!)
            viewBinding.overallPercentageTxt.text = overall.toString()
        }

    }


}