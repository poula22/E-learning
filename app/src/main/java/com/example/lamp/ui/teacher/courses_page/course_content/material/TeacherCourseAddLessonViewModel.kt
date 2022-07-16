package com.example.lamp.ui.teacher.courses_page.course_content.material

import android.util.Log
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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.File

class TeacherCourseAddLessonViewModel:ViewModel() {
    val lessonLiveData= MutableLiveData<LessonResponseDTO>()
    val contentLiveData= MutableLiveData<Response<Void>>()
    val errorMessage= MutableLiveData<String>()
    private val lessonWebService=ApiManager.getLessonApi()
    private val lessonDataOnlineSource: LessonOnlineDataSource =LessonOnlineDataSourceImpl(lessonWebService)
    private val contentWebService=ApiManager.getContentApi()
    private val contentDataOnlineSource: ContentOnlineDataSource = ContentOnlineDataSourceImpl(contentWebService)
    private val materialRepository: MaterialRepository = MaterialRepositoryImpl(lessonDataOnlineSource,contentDataOnlineSource)

    fun addLesson(lessonResponseDTO: LessonResponseDTO){
        viewModelScope.launch {
            try {
                lessonLiveData.value = materialRepository.addLesson(lessonResponseDTO)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.response()?.errorBody()?.string()
                    }
                    else -> {
                        throw t
                    }
                }
            }
        }
    }

    fun addContent(contentResponseDTO: ContentResponseDTO,pdfFile:File?,videoFile:File?){
        runBlocking {
            try {
                val part = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("link", contentResponseDTO.link ?: "")
                    .addFormDataPart("lessonId",contentResponseDTO.lessonId.toString())
                    .addFormDataPart("showDate",contentResponseDTO.showDate ?: "")

                if (videoFile!=null){
                    part.addFormDataPart("videoPath",videoFile.name,videoFile.asRequestBody("video/*".toMediaTypeOrNull()))
//                    Log.v("videoFile",videoFile.totalSpace.toString())
                }

                if (pdfFile!=null){
                    part.addFormDataPart("pdfPath",pdfFile.name,pdfFile.asRequestBody("application/pdf".toMediaTypeOrNull()))
                    Log.v("pdfFile",pdfFile.name)
                }


                val requestBody: RequestBody=part.build()

                Log.v("requestBody",requestBody.contentLength().toString())
                contentLiveData.value = materialRepository.addContent(requestBody)

            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.response()?.errorBody()?.string()
                    }
                    else -> {
                        throw t
                    }

                }
            }
        }
    }

}