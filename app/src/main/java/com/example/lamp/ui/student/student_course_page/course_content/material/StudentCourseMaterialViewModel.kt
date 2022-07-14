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
import com.example.domain.repos.data_sources.ContentOnlineDataSource
import com.example.domain.repos.data_sources.LessonOnlineDataSource
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import retrofit2.HttpException

class StudentCourseMaterialViewModel : ViewModel() {
    var errorMessage = MutableLiveData<String>()
    var VideoLiveData = MutableLiveData<ResponseBody>()
    var LessonsLiveData = MutableLiveData<List<LessonResponseDTO>>()
    var contentLiveData = MutableLiveData<List<ContentResponseDTO>>()

    private val fileService=ApiManager.getFileTransferApi()
    private val lessonWebService = ApiManager.getLessonApi()
    private val contentWebService = ApiManager.getContentApi()
    private val lessonOnlineDataSource: LessonOnlineDataSource = LessonOnlineDataSourceImpl(lessonWebService)
    private val contentOnlineDataSource: ContentOnlineDataSource = ContentOnlineDataSourceImpl(contentWebService)
    private val materialRepository:MaterialRepository=MaterialRepositoryImpl(lessonOnlineDataSource,contentOnlineDataSource)


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
    fun getVideo(fileName:String){
        runBlocking{
            try {
                VideoLiveData.value=fileService.getVideo(fileName)
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