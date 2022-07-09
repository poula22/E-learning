package com.example.lamp.ui.teacher.courses_page.course_content.quiz

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.QuestionChoiceOnlineDataSourceImpl
import com.example.data.repos.data_sources_impl.QuestionOnlineDataSourceImpl
import com.example.data.repos.data_sources_impl.QuizOnlineDataSourceImpl
import com.example.domain.model.QuestionChoiceResponseDTO
import com.example.domain.model.QuestionResponseDTO
import com.example.domain.model.QuizResponseDTO
import com.example.domain.repos.data_sources.QuestionChoiceOnlineDataSource
import com.example.domain.repos.data_sources.QuestionOnlineDataSource
import com.example.domain.repos.data_sources.QuizOnlineDataSource
import com.example.lamp.MainActivity
import com.example.lamp.ui.student.student_course_page.course_content.quiz.quizzes_recycler_view.QuestionItem
import com.example.lamp.ui.teacher.courses_page.course_content.quiz.questions_recycler_view.TeacherQuizQuestionsAdapter
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class FragmentTeacherCourseQuizAddQuestionsViewModel : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val choicesLiveData = MutableLiveData<Response<Void>>()
    val questionLiveData = MutableLiveData<QuestionResponseDTO>()
    val quizLiveData = MutableLiveData<QuizResponseDTO>()
    private val questionService = ApiManager.getQuestionApi()
    private val answerService = ApiManager.getQuestionChoiceApi()
    private val quizDataSource: QuizOnlineDataSource = QuizOnlineDataSourceImpl(ApiManager.getQuizApi())
    private val questionOnlineDataSource: QuestionOnlineDataSource = QuestionOnlineDataSourceImpl(questionService)
    private val choiceOnlineDataSource: QuestionChoiceOnlineDataSource = QuestionChoiceOnlineDataSourceImpl(answerService)
    fun addQuestion(questionResponseDTO: QuestionResponseDTO) {
        viewModelScope.launch {
            try {
                questionLiveData.value=questionOnlineDataSource.addQuestion(questionResponseDTO)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.response()?.errorBody()?.string()
                    }
                    else -> {
                        println("unknown error")
                    }
                }
            }
        }
    }

    fun addChoices(questionChoices : List<QuestionChoiceResponseDTO>) {
        viewModelScope.launch {
            try {
                choicesLiveData.value = choiceOnlineDataSource.postMultipleQuestionChoices(questionChoices)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.response()?.errorBody()?.string()
                    }
                    else -> {
                        println("unknown error")
                    }
                }
            }
        }
    }

    fun updateQuiz(quiz: QuizResponseDTO) {
        viewModelScope.launch {
            try {
                //change 8 to it
                quizLiveData.value= async { quizDataSource.updateQuiz(8,quiz)}.await()
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.response()?.errorBody()?.string()
                    }
                    else -> {
                        println("unknown error")
                    }
                }
            }
        }
    }

    fun postAllQuestions(questions: MutableList<QuestionItem>?, quizResponseDTO: QuizResponseDTO) {
        val postQuestions=questions?.map { question ->
            question.question?.id=0
            question.question?.quizId=quizResponseDTO.id
            question.answers=TeacherQuizQuestionsAdapter.getAnswersListener?.getAnswers()
            question.answers?.forEach {
                Log.v("question", it.toString())
                if (it.isCorrect){
                    question.question?.correctAnswer=it.questionChoiceResponseDTO?.choice
                    return@forEach
                }
            }
            question.question
        }
        Log.v("postQuestions", postQuestions.toString())
        postQuestions?.forEach {
            Handler(Looper.getMainLooper()).postDelayed( {
                viewModelScope.launch {
                    try {
                        questionLiveData.value=async {  it?.let { it1 ->
                            questionOnlineDataSource.addQuestion(
                                it1
                            )
                        }
                        }.await()

                    } catch (t: Throwable) {
                        when (t) {
                            is HttpException -> {
                                errorMessage.value = t.response()?.errorBody()?.string()
                            }
                            else -> {
                                println("unknown error")
                            }
                        }
                    }
                }
            },200)

        }
    }

}