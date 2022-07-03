package com.example.lamp.ui.teacher.courses_page.course_content.settings

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.CourseOnlineDataSourceImpl
import com.example.domain.model.CourseResponseDTO
import com.example.domain.repos.data_sources.CourseOnlineDataSource
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
import java.io.File

class TeacherCourseSettingsViewModel : ViewModel() {
    val  service=ApiManager.getCourseApi()
    val dataSource:CourseOnlineDataSource=CourseOnlineDataSourceImpl(service)
    val liveData= MutableLiveData<CourseResponseDTO>()
    val fileLiveData= MutableLiveData<Response<Void>>()

    fun changeCourseImage(course:CourseResponseDTO){
        viewModelScope.launch {
            try {
                val originalFile = course.courseImage?.let { File(it) }
                originalFile?.name?.let { Log.e("file", it) }
                val filePart=originalFile?.asRequestBody("image/*".toMediaTypeOrNull())
                val file= filePart?.let { MultipartBody.Part.createFormData("photo", originalFile.name, it) }
                file?.let {
                    dataSource.updateCourseImageByCourseId(CONSTANTS.courseId, it)
                }
            }catch (t:Throwable){
                t.printStackTrace()
            }
        }
    }

    fun getCourseById(){
        viewModelScope.launch {
            try {
                liveData.value=dataSource.getCourse(CONSTANTS.courseId)
            }catch (t:Throwable){
                t.printStackTrace()
            }
        }
    }
}