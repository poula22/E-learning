package com.example.lamp.ui.teacher.courses_page.courses_bottom_sheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.CourseOnlineDataSourceImpl
import com.example.domain.model.CourseResponseDTO
import com.example.domain.repos.data_sources.CourseOnlineDataSource
import kotlinx.coroutines.launch
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
                liveData.value=courseOnlineDataSource.addCourse(courseDTO)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {

                    }
                }

            }
        }
    }
}