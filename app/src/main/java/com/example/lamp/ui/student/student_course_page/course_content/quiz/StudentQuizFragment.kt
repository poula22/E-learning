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
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentQuizBinding
import com.example.lamp.ui.student.student_course_page.course_content.quiz.answers_recycler_view.StudentQuizAnswersAdapter
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.answers_recycler_view.AnswerItem
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.quizzes_recycler_view.QuizItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class StudentQuizFragment(var quiz: QuizItem) : Fragment() {
    lateinit var viewBinding: FragmentStudentQuizBinding
    var questionList = quiz.questions
    var studentAnswers = mutableListOf<AnswerItem>()
    var questionIndex = 0
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
        initViews()
    }

    private fun initViews() {
        viewBinding.durationTime.setText(quiz.durationMinutes!!)
        var question = questionList?.get(questionIndex)
        viewBinding.questionCard.item = question
        val adapter = StudentQuizAnswersAdapter(question?.answers!!)
        viewBinding.questionCard.questionAnswerRecyclerView.adapter = adapter
        adapter.onAnswerSelectedListener =
            object : StudentQuizAnswersAdapter.OnAnswerSelectedListener {
                override fun onAnswerSelected(answer: AnswerItem) {
                    TODO("Not yet implemented")
                }

            }
        viewBinding.nextQuestionBtn.setOnClickListener {
            questionIndex++
            if (questionIndex < questionList?.size!!) {
                var question = questionList?.get(questionIndex)
                viewBinding.questionCard.item = question
                adapter.changeData(question?.answers!!)
                if (questionIndex == questionList?.size!! - 1) {
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
            var question = questionList?.get(questionIndex)
            viewBinding.questionCard.item = question
            adapter.changeData(question?.answers!!)
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