package com.example.lamp.ui.teacher.courses_page.course_content.quiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.common_functions.CommonFunctions
import com.example.domain.model.QuestionChoiceResponseDTO
import com.example.domain.model.QuizResponseDTO
import com.example.domain.model.TeacherQuestionWithChoicesResponseDTO
import com.example.domain.model.TeacherQuizResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseQuizAddQuestionsBinding
import com.example.lamp.ui.student.student_course_page.course_content.quiz.quizzes_recycler_view.QuestionItem
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.questions_recycler_view.TeacherQuizQuestionsAdapter
import java.text.SimpleDateFormat
import java.util.*

class TeacherCourseQuizAddQuestionsFragment: Fragment() {

    lateinit var viewBinding: FragmentTeacherCourseQuizAddQuestionsBinding
    lateinit var viewModel: FragmentTeacherCourseQuizAddQuestionsViewModel
    lateinit var adapter: TeacherQuizQuestionsAdapter
    lateinit var quizDetails:QuizResponseDTO
    var questions:MutableList<TeacherQuestionWithChoicesResponseDTO> =mutableListOf()
    lateinit var teacherQuizResponseDTO: TeacherQuizResponseDTO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(FragmentTeacherCourseQuizAddQuestionsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_teacher_course_quiz_add_questions,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initViews()
    }

    private fun subscribeToLiveData() {
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel.quizLiveData.observe(viewLifecycleOwner) {
            if (it.code()==200){
                Log.d("quiz", it.toString())
                Toast.makeText(requireContext(), "quiz added successfully", Toast.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager.popBackStack()
            }

        }
    }

    private fun initViews() {
        adapter = TeacherQuizQuestionsAdapter()
//        adapter.onQuestionAddedListener =
//            object : TeacherQuizQuestionsAdapter.OnQuestionAddedListener {
//                override fun onQuestionAdded(question: QuestionResponseDTO) {
//                    viewModel.addQuiz(question.convertTo(QuestionResponseDTO::class.java))
//                }
//
//            }
        viewBinding.quizEditQuestionsList.adapter = adapter

        if (adapter.itemCount == 0) {
            viewBinding.createQuestionLayout.visibility = View.VISIBLE
        }

        viewBinding.createQuestionButton.setOnClickListener {
            createQuestion(adapter)
        }
        viewBinding.createQuestionFab.setOnClickListener {
            createQuestion(adapter)
        }

        viewBinding.saveBtn.setOnClickListener {
            adapter.notifyDataSetChanged()
            createQuiz()
            createQuestionsAndAnswers()
            createResponse()
            viewModel.addQuiz(teacherQuizResponseDTO)

            //add quiz
//            requireActivity().supportFragmentManager.popBackStack()
        }
        CommonFunctions.onBackPressed(requireActivity(), viewLifecycleOwner, requireContext())
    }

    private fun createResponse() {

        teacherQuizResponseDTO= TeacherQuizResponseDTO(
            quizDetails.instructions,
            quizDetails.postTime,
            quizDetails.grade,
            questions,
            quizDetails.startTime,
            0,
            quizDetails.endTime,
            quizDetails.title,
            CONSTANTS.courseId
        )

        Log.e("quiz", teacherQuizResponseDTO.toString())

    }

    private fun createQuestionsAndAnswers() {
        adapter.questions?.forEach {
            var correctAnswer:QuestionChoiceResponseDTO?=null
            it.answers?.forEach{
                    if(it.isCorrect){
                        correctAnswer=it.questionChoiceResponseDTO
                    }
            }
            questions.add(
                TeacherQuestionWithChoicesResponseDTO(
                    quizDetails.id,
                    it.answers?.map { answer ->
                        answer.questionChoiceResponseDTO
                    },
                    0,
                    it.question?.title,
                    correctAnswer?.choice,
                    quizDetails.startTime
                )
            )
        }
    }

    private fun createQuestion(adapter: TeacherQuizQuestionsAdapter) {
        if (viewBinding.createQuestionLayout.isVisible) {
            viewBinding.createQuestionLayout.visibility = View.GONE
            viewBinding.quizEditQuestionsList.visibility = View.VISIBLE
        }
        val question = QuestionItem()
        adapter.addQuestion(question)
    }

    private fun createQuiz() {

        val d= SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val date=d.format(Date())
        Log.e("date",date)
        quizDetails=QuizResponseDTO(
            viewBinding.quizInstructions.editText?.text.toString()
            ,viewBinding.grade.text.toString().toInt()
            ,date
            ,0
            ,date
            ,date
            ,viewBinding.quizTitleEdit.text.toString()
            ,CONSTANTS.courseId)

    }

    override fun onStart() {
        super.onStart()
        var toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.isVisible = false
        var drawerLayout: DrawerLayout = requireActivity().findViewById(R.id.drawer_layout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }


}