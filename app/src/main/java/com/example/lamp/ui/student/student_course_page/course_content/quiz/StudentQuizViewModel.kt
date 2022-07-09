package com.example.lamp.ui.student.student_course_page.course_content.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.QuestionOnlineDataSourceImpl
import com.example.domain.model.QuizDetailsResponseDTO
import kotlinx.coroutines.launch
import retrofit2.HttpException

class StudentQuizViewModel : ViewModel() {
    val liveData = MutableLiveData<List<QuizDetailsResponseDTO>>()
    val errorMessage = MutableLiveData<String>()
    private val webService = ApiManager.getQuestionApi()
    private val onlineDataSource = QuestionOnlineDataSourceImpl(webService)
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
}