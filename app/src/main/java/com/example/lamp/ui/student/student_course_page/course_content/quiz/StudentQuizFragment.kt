package com.example.lamp.ui.student.student_course_page.course_content.quiz

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.domain.model.QuestionAnswerResponseDTO
import com.example.domain.model.QuestionChoiceResponseDTO
import com.example.domain.model.QuizDetailsResponseDTO
import com.example.domain.model.QuizGradeResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentQuizBinding
import com.example.lamp.ui.student.student_course_page.course_content.quiz.answers_recycler_view.StudentQuizAnswersAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class StudentQuizFragment : Fragment() {
    lateinit var viewBinding: FragmentStudentQuizBinding
    var studentAnswers :MutableList<QuestionChoiceResponseDTO> = mutableListOf()
    var questionIndex = 0
    lateinit var quiz: List<QuizDetailsResponseDTO>
    lateinit var viewModel: StudentQuizViewModel
    lateinit var adapter: StudentQuizAnswersAdapter
    var quizDuration = 1
    var quizId=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StudentQuizViewModel::class.java)
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
        quizId = requireArguments().getInt("quizId")
        quizDuration = requireArguments().getInt("duration")
        if (quizDuration==0) quizDuration=1
        subscribeToLiveData()
        initViews()
        viewModel.getQuizQuestions(quizId)
    }

    private fun subscribeToLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner) { list ->
            quiz = list
            var question = list[questionIndex]
            viewBinding.questionCard.item = question
            adapter.changeData(question.questionChoices)
        }
        viewModel.answerLiveData.observe(viewLifecycleOwner) {
            viewModel.gradeQuiz(QuizGradeResponseDTO(CONSTANTS.user_id,quizId))
        }
        viewModel.gradeLiveData.observe(viewLifecycleOwner) {
            if (it.code()==200)
                requireActivity().supportFragmentManager.popBackStack()
            else
                Toast.makeText(requireContext(),it.errorBody()?.string(),Toast.LENGTH_LONG).show()
        }
    }

    private fun initViews() {
        startCountDownTimer(quizDuration * 60, viewBinding.durationTime)
        adapter = StudentQuizAnswersAdapter()
        viewBinding.durationTime.setText(quizDuration.toString())
        viewBinding.questionCard.questionAnswerRecyclerView.adapter = adapter
        adapter.onAnswerSelectedListener =
            object : StudentQuizAnswersAdapter.OnAnswerSelectedListener {
                override fun onAnswerSelected(answer: QuestionChoiceResponseDTO) {
                    if (studentAnswers.size==questionIndex){
                        studentAnswers.add(answer)
                        return
                    }
                    studentAnswers.set(questionIndex,answer)
                }

            }
        viewBinding.nextQuestionBtn.setOnClickListener {
            questionIndex++
            if (questionIndex < quiz.size && questionIndex >-1) {
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
//                FragmentStudentQuizFinishedStats(studentAnswers, quiz)
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Are you Sure you want to Submit Answers?")
                    .setMessage("If you clicked on back button, you will NOT be able to enter exam again")
                    .setNeutralButton("Cancel") { dialog, which ->
                        // Respond to neutral button press
                    }
                    .setNegativeButton("Submit and exit") { dialog, which ->
                        Toast.makeText(context, "Answers Submitted", Toast.LENGTH_SHORT)
                            .show()
                        val request=studentAnswers.map {
                            QuestionAnswerResponseDTO(
                                CONSTANTS.user_id,
                                it.questionId,
                                it.choice,
                                0
                            )
                        }
                        Log.e("request",request.toString())
                        viewModel.sendAnswers(
                            request
                        )
                    }
                    .show()


            }

        }
        viewBinding.previousQuestionBtn.setOnClickListener {
            it.isVisible = (questionIndex != 0)
            if (questionIndex>0){
                questionIndex--
                var question = quiz[questionIndex]
                viewBinding.questionCard.item = question
                adapter.changeData(question.questionChoices)
            }

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
            }
        )


    }


    fun startCountDownTimer(Seconds: Int, tv: TextView) {
        object : CountDownTimer((Seconds * 1000 + 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var seconds = (millisUntilFinished / 1000).toInt()
                val hours = seconds / (60 * 60)
                val tempMint = seconds - hours * 60 * 60
                val minutes = tempMint / 60
                if (minutes < 2) {
                    tv.setTextColor(Color.RED)
                }
                seconds = tempMint - minutes * 60
                tv.text = "TIME : " + String.format("%02d", hours) + ":" + String.format(
                    "%02d",
                    minutes
                ) + ":" + String.format("%02d", seconds)
            }

            override fun onFinish() {
                tv.text = "Completed"
                viewModel.sendAnswers(
                    studentAnswers.map {
                        QuestionAnswerResponseDTO(
                            CONSTANTS.courseId,
                            it.questionId,
                            it.choice,
                            it.id
                        )
                    }
                )
            }
        }.start()
    }


    override fun onStart() {
        super.onStart()
        var toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.isVisible = false
        var drawerLayout: DrawerLayout = requireActivity().findViewById(R.id.drawer_layout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)


    }
}