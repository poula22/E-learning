package com.example.lamp.ui.teacher.courses_page.course_content.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.QuizOnlineDataSourceImpl
import com.example.domain.model.TeacherQuizResponseDTO
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TeacherCourseQuizzesViewModel : ViewModel() {

    val liveData = MutableLiveData<List<TeacherQuizResponseDTO>>()
    val deleteLiveData = MutableLiveData<TeacherQuizResponseDTO>()
    val errorMessage = MutableLiveData<String>()
    private val quizService = ApiManager.getQuizApi()
    private val quizOnlineDataSource = QuizOnlineDataSourceImpl(quizService)
    fun getAllQuizzes() {
        viewModelScope.launch {
            try {
                liveData.value = quizOnlineDataSource.getQuizzesByCourseId(CONSTANTS.courseId)
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

    fun deleteQuiz(quiz: TeacherQuizResponseDTO) {
        viewModelScope.launch {
            try {
                deleteLiveData.value = quiz.id?.let { quizOnlineDataSource.deleteQuiz(it) }
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