package com.example.lamp.ui.student.student_course_page.course_content.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.QuestionAnswerOnlineDataSourceImpl
import com.example.data.repos.data_sources_impl.QuestionOnlineDataSourceImpl
import com.example.domain.model.QuestionAnswerResponseDTO
import com.example.domain.model.QuizDetailsResponseDTO
import com.example.domain.repos.data_sources.QuestionAnswerOnlineDataSource
import com.example.domain.repos.data_sources.QuestionOnlineDataSource
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class StudentQuizViewModel : ViewModel() {
    val liveData = MutableLiveData<List<QuizDetailsResponseDTO>>()
    val answerLiveData = MutableLiveData<Response<Void>>()
    val errorMessage = MutableLiveData<String>()
    private val webService = ApiManager.getQuestionApi()
    private val onlineDataSource: QuestionOnlineDataSource = QuestionOnlineDataSourceImpl(webService)
    private val answersOnlineDataSource:QuestionAnswerOnlineDataSource = QuestionAnswerOnlineDataSourceImpl(ApiManager.getQuestionAnswerApi())
    fun getQuizQuestions(quizId: Int) {
        viewModelScope.launch {
            try {

                liveData.value = onlineDataSource.getQuestionsByQuizId(quizId)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.response()?.errorBody()?.string()
                    }
                    else -> {
                        errorMessage.value = "error"
                    }
                }
            }

        }
    }

    fun sendAnswers(questionsAnswers: List<QuestionAnswerResponseDTO>){
        viewModelScope.launch {
            try {

                answerLiveData.value = answersOnlineDataSource.postMultipleQuestionAnswers(questionsAnswers)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.response()?.errorBody()?.string()
                    }
                    else -> {
                        errorMessage.value = "error"
                    }
                }
            }

        }

    }

}