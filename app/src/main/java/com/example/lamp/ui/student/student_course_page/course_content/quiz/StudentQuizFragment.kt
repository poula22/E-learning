package com.example.lamp.ui.student.student_course_page.course_content.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.data.model.QuestionChoiceResponse
import com.example.data.model.QuizDetailsResponse
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentQuizBinding
import com.example.lamp.ui.student.student_course_page.course_content.quiz.answers_recycler_view.StudentQuizAnswersAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class StudentQuizFragment : Fragment() {
    lateinit var viewBinding: FragmentStudentQuizBinding
    var studentAnswers = mutableListOf<QuestionChoiceResponse>()
    var questionIndex = 0
    lateinit var quiz: List<QuizDetailsResponse>
    lateinit var viewModel: StudentQuizViewModel
    lateinit var adapter: StudentQuizAnswersAdapter
    var quizDuration=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(StudentQuizViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_student_quiz, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var quizId=requireArguments().getInt("quizId")
        subscribeToLiveData()
        initViews()
        viewModel.getQuizQuestions(quizId)
    }

    private fun subscribeToLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner) { list->
            quiz=list
            var question = list[questionIndex]
            viewBinding.questionCard.item = question
            adapter = StudentQuizAnswersAdapter()
        }
    }

    private fun initViews() {
        adapter=StudentQuizAnswersAdapter()
        viewBinding.durationTime.setText(quizDuration)
        adapter = StudentQuizAnswersAdapter()
        viewBinding.questionCard.questionAnswerRecyclerView.adapter = adapter
        adapter.onAnswerSelectedListener =
            object : StudentQuizAnswersAdapter.OnAnswerSelectedListener {
                override fun onAnswerSelected(answer: QuestionChoiceResponse) {
                    studentAnswers.add(answer)
                }

            }
        viewBinding.nextQuestionBtn.setOnClickListener {
            questionIndex++
            if (questionIndex < quiz.size) {
                var question = quiz[questionIndex]
                viewBinding.questionCard.item = question
                adapter.changeData(question.questionChoices)
                if (questionIndex == quiz.size - 1) {
                    viewBinding.nextQuestionBtn.setText("submit")
                    viewBinding.nextQuestionBtn.setBackgroundResource(R.color.green)

                } else {
                    viewBinding.nextQuestionBtn.setText("next")
                    viewBinding.nextQuestionBtn.setBackgroundResource(R.color.yellow)
                }

            } else {
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.student_course_content_container,
                        FragmentStudentQuizFinishedStats(studentAnswers, quiz)
                    )
                    .commit()

            }

        }
        viewBinding.previousQuestionBtn.setOnClickListener {
            it.isVisible = (questionIndex != 0)
            questionIndex--
            var question = quiz[questionIndex]
            viewBinding.questionCard.item = question
            adapter.changeData(question.questionChoices)
        }


        // on Back pressed
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Are you Sure you want to Submit Answers?")
                        .setMessage("If you clicked on back button, you will NOT be able to enter exam again")
                        .setNeutralButton("Cancel") { dialog, which ->
                            // Respond to neutral button press
                        }
                        .setNegativeButton("Submit and exit") { dialog, which ->
                            Toast.makeText(context, "Answers Submitted", Toast.LENGTH_SHORT)
                                .show()
                            requireActivity().supportFragmentManager.popBackStack()
                        }
                        .show()

                }
            })


    }

    override fun onStart() {
        super.onStart()
        var toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.isVisible = false
        var drawerLayout: DrawerLayout = requireActivity().findViewById(R.id.drawer_layout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }
}