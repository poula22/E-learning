package com.example.lamp.ui.teacher.courses_page.course_content.material

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
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File

class TeacherCourseEditMaterialViewModel:ViewModel() {
    private val lessonOnlineDataSource: LessonOnlineDataSource = LessonOnlineDataSourceImpl(ApiManager.getLessonApi())
    private val contentOnlineDataSource: ContentOnlineDataSource = ContentOnlineDataSourceImpl(ApiManager.getContentApi())
    private val repository: MaterialRepository = MaterialRepositoryImpl(lessonOnlineDataSource,contentOnlineDataSource)
    var errorMessage = MutableLiveData<String>()
    var lessonLiveData = MutableLiveData<LessonResponseDTO>()
    var contentLiveData = MutableLiveData<List<ContentResponseDTO>>()
    var filesLiveData = MutableLiveData<ContentResponseDTO>()


    fun updateLesson(lessonId: Int, lesson: LessonResponseDTO) {
        viewModelScope.launch {
            try {
                lessonLiveData.value = repository.updateLesson(lessonId, lesson)
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
                }
            }
        }
    }

    fun updateContent(id: Int, pdfFile: File?, videoFile: File?,link:String?) {
            runBlocking {
                try {
                    val part = MultipartBody.Builder()
                        .setType(MultipartBody.FORM)


                    if (link!=null){
                        part.addFormDataPart("link", link)
                    }
                    if (pdfFile!=null){
                        part.addFormDataPart("pdfPath",pdfFile.name,pdfFile.asRequestBody("application/pdf".toMediaTypeOrNull()))
                    }
                    if (videoFile!=null){
                        part.addFormDataPart("videoPath",videoFile.name,videoFile.asRequestBody("video/*".toMediaTypeOrNull()))
                    }

                    val requestBody: RequestBody =part.build()
                    filesLiveData.value = repository.updateContentFileByContentId(id,requestBody)

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