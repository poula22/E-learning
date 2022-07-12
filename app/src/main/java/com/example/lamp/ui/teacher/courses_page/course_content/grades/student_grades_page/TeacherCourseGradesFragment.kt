package com.example.lamp.ui.teacher.courses_page.course_content.grades.student_grades_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.StudentResponseDTO
import com.example.lamp.databinding.FragmentTeacherCourseGradesStudentContentBinding
import com.example.lamp.ui.teacher.courses_page.course_content.grades.student_grades_page.assignments_rv.AssignmentsGradesAdapter
import com.example.lamp.ui.teacher.courses_page.course_content.grades.student_grades_page.quizzes_rv.QuizzesGradesAdapter

class TeacherCourseGradesFragment(
    val student: StudentResponseDTO
) : Fragment() {

    lateinit var viewBinding: FragmentTeacherCourseGradesStudentContentBinding
    lateinit var assignmentsGradesAdapter: AssignmentsGradesAdapter
    lateinit var quizzesGradesAdapter: QuizzesGradesAdapter
    lateinit var viewModel: TeacherCourseGradesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeacherCourseGradesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            com.example.lamp.R.layout.fragment_teacher_course_grades_student_content,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initViews()
        viewModel.getGradesByStudentId(student.id!!)
    }

    private fun subscribeToLiveData() {
        viewModel.assignmentsLiveData.observe(viewLifecycleOwner) {
            assignmentsGradesAdapter.changeData(it)
        }

        viewModel.quizzesLiveData.observe(viewLifecycleOwner) {
            quizzesGradesAdapter.changeData(it)
        }

    }

    fun initViews() {
        assignmentsGradesAdapter = AssignmentsGradesAdapter()
        quizzesGradesAdapter = QuizzesGradesAdapter()
        viewBinding.assignmentsRv.adapter = assignmentsGradesAdapter
        viewBinding.quizzesRv.adapter = quizzesGradesAdapter

//         overall grades equals sum of assignments and quizzes grades divided by items count
        val overallGrades =
            (assignmentsGradesAdapter.assignmentsGrades?.sumOf { it.grade!! }
                    )?.plus((quizzesGradesAdapter.quizzesGrades?.sumOf { it.grade!! }!!))
        val itemsCount = (assignmentsGradesAdapter.assignmentsGrades?.size
                )?.plus((quizzesGradesAdapter.quizzesGrades?.size!!))
        if (itemsCount == 0 || overallGrades == null) {
            viewBinding.progressBar.visibility = View.GONE
            viewBinding.overallPercentageTxt.text = "No grades"
        } else {
            var overall = overallGrades.div(itemsCount!!)
            viewBinding.overallPercentageTxt.text = overall.toString()
        }


        viewBinding.studentName.text = student.firstName + " " + student.lastName

    }
}