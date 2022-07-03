package com.example.lamp.ui.student.student_course_page.course_content.material

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.MaterialRepositoryImpl
import com.example.data.repos.data_sources_impl.ContentOnlineDataSourceImpl
import com.example.data.repos.data_sources_impl.LessonOnlineDataSourceImpl
import com.example.domain.model.ContentResponseDTO
import com.example.domain.model.LessonResponseDTO
import com.example.domain.repos.MaterialRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class StudentCourseMaterialViewModel : ViewModel() {
    var errorMessage = MutableLiveData<String>()
    val lessonWebService = ApiManager.getLessonApi()
    val contentWebService = ApiManager.getContentApi()
    val lessonOnlineDataSource = LessonOnlineDataSourceImpl(lessonWebService)
    val contentOnlineDataSource = ContentOnlineDataSourceImpl(contentWebService)
    val materialRepository:MaterialRepository=MaterialRepositoryImpl(lessonOnlineDataSource,contentOnlineDataSource)
    var LessonsLiveData = MutableLiveData<List<LessonResponseDTO>>()
    var contentLiveData = MutableLiveData<List<ContentResponseDTO>>()

    fun getCourseLessons(courseId: Int) {
        viewModelScope.launch {
            try {
                LessonsLiveData.value = materialRepository.getLessonsByCourseId(courseId)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.response()?.errorBody()?.string()
                    }
                }
            }

        }
    }

    fun getLessonContent(lessonId: Int) {
        viewModelScope.launch {
            try {
                contentLiveData.value = materialRepository.getContentsByLessonId(lessonId)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.response()?.errorBody()?.string()
                    }
                }
            }
        }
    }


}