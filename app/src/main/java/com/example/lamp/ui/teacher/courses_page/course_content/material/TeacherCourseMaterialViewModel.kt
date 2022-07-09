package com.example.lamp.ui.teacher.courses_page.course_content.material

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.ContentOnlineDataSourceImpl
import com.example.data.repos.data_sources_impl.LessonOnlineDataSourceImpl
import com.example.domain.model.ContentResponseDTO
import com.example.domain.model.LessonResponseDTO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.HttpException

class TeacherCourseMaterialViewModel : ViewModel() {
    var errorMessage = MutableLiveData<String>()
    private val lessonWebService = ApiManager.getLessonApi()
    private val contentWebService = ApiManager.getContentApi()
    private val lessonOnlineDataSource = LessonOnlineDataSourceImpl(lessonWebService)
    private val contentOnlineDataSource = ContentOnlineDataSourceImpl(contentWebService)
    private val fileService=ApiManager.getFileTransferApi()
    var LessonsLiveData = MutableLiveData<List<LessonResponseDTO>>()
    var contentLiveData = MutableLiveData<List<ContentResponseDTO>>()
    var VideoLiveData = MutableLiveData<ResponseBody>()

    fun getCourseLessons(courseId: Int) {
        viewModelScope.launch {
            try {
                LessonsLiveData.value = lessonOnlineDataSource.getLessonsByCourseId(courseId)
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
                contentLiveData.value = contentOnlineDataSource.getContentsByLessonId(lessonId)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.response()?.errorBody()?.string()
                    }
                }
            }
        }
    }
    fun getVideo(url:String){
        runBlocking{
            try {
                VideoLiveData.value=fileService.getVideo(url)
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