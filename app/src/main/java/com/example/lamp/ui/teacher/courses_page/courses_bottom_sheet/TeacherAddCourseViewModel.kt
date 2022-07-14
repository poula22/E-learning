package com.example.lamp.ui.teacher.courses_page.courses_bottom_sheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.CourseOnlineDataSourceImpl
import com.example.domain.model.CourseResponseDTO
import com.example.domain.repos.data_sources.CourseOnlineDataSource
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import retrofit2.Response

class TeacherAddCourseViewModel : ViewModel() {
    var courseWebService = ApiManager.getCourseApi()
    var courseOnlineDataSource: CourseOnlineDataSource = CourseOnlineDataSourceImpl(courseWebService)
    var flag = false
    val liveData=MutableLiveData<Response<Void>>()

    fun AddCourse(courseDTO: CourseResponseDTO) {
        viewModelScope.launch {
            try {
                val body:RequestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("courseName",courseDTO.courseName?:"")
                    .addFormDataPart("courseDescription",courseDTO.courseDescription?:"")
                    .addFormDataPart("teacherId",CONSTANTS.user_id.toString())
                    .build()
                liveData.value=courseOnlineDataSource.addCourse(body)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {

                    }
                }

            }
        }
    }
}