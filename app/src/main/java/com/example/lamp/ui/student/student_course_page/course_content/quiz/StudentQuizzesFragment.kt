package com.example.lamp.ui.student.student_course_page.course_content.quiz

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
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentCourseQuizzesBinding
import com.example.lamp.ui.student.student_course_page.course_content.quiz.quizzes_recycler_view.StudentQuizAdapter
import com.example.lamp.ui.student.student_course_page.course_content.quiz.quizzes_recycler_view.StudentQuizItem
import java.time.LocalDateTime

class StudentQuizzesFragment : Fragment() {
    lateinit var viewBinding: FragmentStudentCourseQuizzesBinding
    lateinit var viewModel: StudentCourseQuizzesViewModel
    var courseId: Int = CONSTANTS.courseId
    val adapter = StudentQuizAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StudentCourseQuizzesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_student_course_quizzes,
                container,
                false
            )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initViews()
        viewModel.getAllQuizzes(courseId)
    }

    private fun subscribeToLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner) { list ->
            val swap = list.map { quiz -> StudentQuizItem(quiz) }.toMutableList()
            Log.v("swap", swap.toString())
            val current = LocalDateTime.now()

//            for (i in 0 until swap.size) {
//                val quiz=swap[i]
//                val posTime = quiz.quizResponseDTO.postTime?.let { LocalDateTime.parse(it) }
//                val startTime = quiz.quizResponseDTO.startTime?.let { LocalDateTime.parse(it) }
//                val endTime = quiz.quizResponseDTO.endTime?.let { LocalDateTime.parse(it) }
//                if (quiz.quizResponseDTO.grade != null) {
//                    quiz.quizResponseDTO.grade?.let { grade ->
//                        if (grade >= 0) {
//                            swap.remove(quiz)
//                        }
//
//                    }
//                } else if (posTime != null || endTime != null) {
//                    Log.d("post", "posTime: $posTime")
//                    Log.d("current", "current: $current")
//                    Log.d("endTime", "endTime: $endTime")
//                    if (current.isBefore(posTime) || current.isAfter(endTime)) {
//                        swap.remove(quiz)
//                    } else if (startTime != null && endTime != null) {
//                        Log.d("endTime", "endTime: $endTime")
//                        Log.d("startTime", "startTime: $startTime")
//                        quiz.duration =
//                            "" + (Math.abs(endTime.hour - startTime.hour) * 60 + Math.abs(endTime.minute - startTime.minute))
//                        Log.v("duration", quiz.duration.toString())
//                    }
//                }
//            }
            swap.forEach { quiz ->
                val posTime = quiz.quizResponseDTO.postTime?.let { LocalDateTime.parse(it) }
                val startTime = quiz.quizResponseDTO.startTime?.let { LocalDateTime.parse(it) }
                val endTime = quiz.quizResponseDTO.endTime?.let { LocalDateTime.parse(it) }
                if (quiz.quizResponseDTO.grade != null) {
                    quiz.quizResponseDTO.grade?.let { grade ->
                        if (grade >= 0) {
                            swap.remove(quiz)
                        }

                    }
                } else if (posTime != null || endTime != null) {
                    Log.d("post", "posTime: $posTime")
                    Log.d("current", "current: $current")
                    Log.d("endTime", "endTime: $endTime")
//                    if (current.isBefore(posTime) || current.isAfter(endTime)) {
//                        swap.remove(quiz)
//                    } else
                        if (startTime != null && endTime != null) {
                            Log.d("endTime", "endTime: $endTime")
                            Log.d("startTime", "startTime: $startTime")
                        quiz.duration =
                            "" + (Math.abs(endTime.hour - startTime.hour) * 60 + Math.abs(endTime.minute - startTime.minute))
                        Log.v("duration", quiz.duration.toString())
                    }
                }
            }
            adapter.changeData(swap)
        }
    }

    private fun initViews() {
        adapter.onStartExamListener = object : StudentQuizAdapter.OnStartExamListener {
            override fun onStartExam(quizId: Int, position: Int, duration: String) {
                goToExam(quizId, position,duration.toInt())
            }

        }
        viewBinding.createDraftRecycler.adapter = adapter


    }

    private fun goToExam(quizId: Int, position: Int, duration: Int) {
        val quiz = viewModel.liveData.value?.get(position)
        val current = LocalDateTime.now()
        val startTime = quiz?.startTime?.let { LocalDateTime.parse(it) }
        if (startTime != null) {
            if (current.isBefore(startTime)) {
                Toast.makeText(requireContext(), "can't start exam now", Toast.LENGTH_SHORT).show()
            } else {
                var bundle = Bundle()
                bundle.putInt("courseId", courseId)
                bundle.putInt("quizId", quizId)
                bundle.putInt("duration",duration)
                var fragmentSwap = StudentQuizFragment()
                fragmentSwap.arguments = bundle
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.student_course_content_container, fragmentSwap
                    )
                    .addToBackStack("")
                    .commit()
            }

        } else {
            var bundle = Bundle()
            bundle.putInt("courseId", courseId)
            bundle.putInt("quizId", quizId)
            bundle.putInt("duration",duration)
            var fragmentSwap = StudentQuizFragment()
            fragmentSwap.arguments = bundle
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.student_course_content_container, fragmentSwap
                )
                .addToBackStack("")
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        var toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.isVisible = true
        var drawerLayout: DrawerLayout = requireActivity().findViewById(R.id.drawer_layout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }


}