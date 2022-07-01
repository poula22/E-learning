package com.example.lamp.ui.teacher.courses_page.course_content.quiz

import android.os.Bundle
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
import com.example.commonFunctions.CommonFunctions
import com.example.data.model.QuestionResponse
import com.example.data.model.QuizResponse
import com.example.data.model.convertTo
import com.example.domain.model.QuestionResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherCourseQuizAddQuestionsBinding
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.questions_recycler_view.QuestionItem
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.questions_recycler_view.TeacherQuizQuestionsAdapter
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.quizzes_recycler_view.QuizItem

class TeacherCourseQuizAddQuestionsFragment(var quiz: QuizResponse) : Fragment() {

    lateinit var viewBinding: FragmentTeacherCourseQuizAddQuestionsBinding
    lateinit var viewModel: FragmentTeacherCourseQuizAddQuestionsViewModel
    lateinit var adapter: TeacherQuizQuestionsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentTeacherCourseQuizAddQuestionsViewModel::class.java)
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
        viewModel.errorMessage.observe(viewLifecycleOwner){

        }
        viewModel.liveData.observe(viewLifecycleOwner){
            //pass answers to adapter
            adapter.changeAnswers(it)
        }
    }

    private fun initViews() {
        viewBinding.quizTitleEdit.setText(quiz.title)
        viewBinding.quizInstructionsEdit.setText(quiz.instructions)
        val adapter = TeacherQuizQuestionsAdapter()
        adapter.onQuestionAddedListener =
            object : TeacherQuizQuestionsAdapter.OnQuestionAddedListener {
                override fun onQuestionAdded(question: QuestionResponse) {
                    viewModel.addQuiz(question.convertTo(QuestionResponseDTO::class.java))
                }

            }
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
            Toast.makeText(context, "Saved successfully", Toast.LENGTH_SHORT).show()
            //insert in database
            requireActivity().supportFragmentManager.popBackStack()
        }
        CommonFunctions.onBackPressed(requireActivity(), viewLifecycleOwner, requireContext())
    }

    private fun createQuestion(adapter: TeacherQuizQuestionsAdapter) {
        if (viewBinding.createQuestionLayout.isVisible) {
            viewBinding.createQuestionLayout.visibility = View.GONE
            viewBinding.quizEditQuestionsList.visibility = View.VISIBLE
        }
        var question = QuestionResponse(null, null, null)
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