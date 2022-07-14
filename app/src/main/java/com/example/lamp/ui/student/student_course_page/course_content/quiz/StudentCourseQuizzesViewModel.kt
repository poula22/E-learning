package com.example.lamp.ui.student.student_course_page.course_content.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.api.QuizWebServiceForGrades
import com.example.data.repos.data_sources_impl.QuizOnlineDataSourceImpl
import com.example.domain.model.QuizResponseDTO
import kotlinx.coroutines.launch
import retrofit2.HttpException

class StudentCourseQuizzesViewModel : ViewModel() {
    var webService: QuizWebServiceForGrades = ApiManager.getQuizApi()
    var quizOnlineDataSource = QuizOnlineDataSourceImpl(webService)
    var liveData = MutableLiveData<List<QuizResponseDTO>>()
    var errorMessage = MutableLiveData<String>()
    fun getAllQuizzes(courseId: Int) {
        viewModelScope.launch {
            try {
                var result = quizOnlineDataSource.getQuizzesByCourseId(courseId)
                liveData.value = result
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