package com.example.lamp.ui.teacher.courses_page.course_content.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.QuestionChoiceOnlineDataSourceImpl
import com.example.data.repos.data_sources_impl.QuestionOnlineDataSourceImpl
import com.example.domain.model.QuestionChoiceResponseDTO
import com.example.domain.model.QuestionResponseDTO
import kotlinx.coroutines.launch
import retrofit2.HttpException

class FragmentTeacherCourseQuizAddQuestionsViewModel : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val liveData = MutableLiveData<List<QuestionChoiceResponseDTO>>()
    val questionService = ApiManager.getQuestionApi()
    val answerService = ApiManager.getQuestionChoiceApi()
    val questionOnlineDataSource = QuestionOnlineDataSourceImpl(questionService)
    val answerOnlineDataSource = QuestionChoiceOnlineDataSourceImpl(answerService)
    fun addQuiz(questionResponse: QuestionResponseDTO) {
        viewModelScope.launch {
            try {
                questionOnlineDataSource.addQuestion(questionResponse)
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

    fun getQuestionAnswers(questionId: Int) {
        viewModelScope.launch {
            try {
                liveData.value = answerOnlineDataSource.getQuestionChoicesByQuestionId(questionId)
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

}