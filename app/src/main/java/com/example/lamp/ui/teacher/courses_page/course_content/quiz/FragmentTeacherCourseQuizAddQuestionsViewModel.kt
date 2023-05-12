package com.example.lamp.ui.teacher.courses_page.course_content.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.QuizOnlineDataSourceImpl
import com.example.domain.model.TeacherQuizResponseDTO
import com.example.domain.repos.data_sources.QuizOnlineDataSource
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class FragmentTeacherCourseQuizAddQuestionsViewModel : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val quizLiveData = MutableLiveData<Response<Void>>()

    val quizDataSource:QuizOnlineDataSource=QuizOnlineDataSourceImpl(ApiManager.getQuizApi())

    fun addQuiz(teacherQuizResponseDTO: TeacherQuizResponseDTO) {
        viewModelScope.launch {
            try {
                quizLiveData.value = quizDataSource.createQuiz(teacherQuizResponseDTO)
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