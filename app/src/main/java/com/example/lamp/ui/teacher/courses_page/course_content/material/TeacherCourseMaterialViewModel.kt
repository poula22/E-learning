package com.example.lamp.ui.teacher.courses_page.course_content.material

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
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
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class TeacherCourseMaterialViewModel : ViewModel() {
    var errorMessage = MutableLiveData<String>()
    private val lessonWebService = ApiManager.getLessonApi()
    private val contentWebService = ApiManager.getContentApi()
    private val fileService=ApiManager.getFileTransferApi()

    private val lessonOnlineDataSource: LessonOnlineDataSource = LessonOnlineDataSourceImpl(lessonWebService)
    private val contentOnlineDataSource: ContentOnlineDataSource = ContentOnlineDataSourceImpl(contentWebService)
    private val repository:MaterialRepository=MaterialRepositoryImpl(lessonOnlineDataSource,contentOnlineDataSource)

    var LessonsLiveData = MutableLiveData<List<LessonResponseDTO>>()
    var removeLessonLiveData=MutableLiveData<Response<Void>>()
    var contentLiveData = MutableLiveData<List<ContentResponseDTO>>()
    var VideoLiveData = MutableLiveData<ResponseBody>()

    fun getCourseLessons() {
        viewModelScope.launch {
            try {
                LessonsLiveData.value = repository.getLessonsByCourseId(CONSTANTS.courseId)
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
                contentLiveData.value = repository.getContentsByLessonId(lessonId)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.response()?.errorBody()?.string()
                    }
                    else -> {
                        errorMessage.value = t.message
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

    fun removeLesson(lesson: LessonResponseDTO?) {
        viewModelScope.launch {
            try {
                lesson?.id?.let {
                    removeLessonLiveData.value=repository.deleteLesson(it)
                }
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