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
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseQuizAddQuestionsBinding
import com.example.lamp.ui.student.student_course_page.course_content.quiz.quizzes_recycler_view.QuestionItem
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.questions_recycler_view.TeacherQuizQuestionsAdapter

class TeacherCourseQuizAddQuestionsFragment(var quiz: QuizResponseDTO) : Fragment() {

    lateinit var viewBinding: FragmentTeacherCourseQuizAddQuestionsBinding
    lateinit var viewModel: FragmentTeacherCourseQuizAddQuestionsViewModel
    lateinit var adapter: TeacherQuizQuestionsAdapter
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

        }
        viewModel.quizLiveData.observe(viewLifecycleOwner) {
            Log.d("quiz", it.toString())

            viewModel.postAllQuestions(adapter.questions,it)

        }
        viewModel.questionLiveData.observe(viewLifecycleOwner) {
//            Toast.makeText(requireContext(), "quiz added successfully", Toast.LENGTH_SHORT).show()
            //get question answers from adapter
            val answers = adapter.getQuestion(it)?.answers
            val postedAnswers=answers?.map { answerItem ->
                answerItem.questionChoiceResponseDTO?.id=it.id
                answerItem.questionChoiceResponseDTO
            }
            viewModel.addChoices(postedAnswers as List<QuestionChoiceResponseDTO>)
        }
        viewModel.choicesLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.code(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViews() {
        viewBinding.quizTitleEdit.setText(quiz.title)
        viewBinding.quizInstructionsEdit.setText(quiz.instructions)
        val adapter = TeacherQuizQuestionsAdapter()
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
            val quiz=QuizResponseDTO(
                id=8,
                title = viewBinding.quizTitleEdit.text.toString(),
                instructions = viewBinding.quizInstructionsEdit.text.toString(),
                courseId = CONSTANTS.courseId

            )
            viewModel.updateQuiz(quiz)

            //add quiz
//            requireActivity().supportFragmentManager.popBackStack()
        }
        CommonFunctions.onBackPressed(requireActivity(), viewLifecycleOwner, requireContext())
    }

    private fun createQuestion(adapter: TeacherQuizQuestionsAdapter) {
        if (viewBinding.createQuestionLayout.isVisible) {
            viewBinding.createQuestionLayout.visibility = View.GONE
            viewBinding.quizEditQuestionsList.visibility = View.VISIBLE
        }
        val question = QuestionItem()
        adapter.addQuestion(question)
    }

    override fun onStart() {
        super.onStart()
        var toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.isVisible = false
        var drawerLayout: DrawerLayout = requireActivity().findViewById(R.id.drawer_layout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }


}