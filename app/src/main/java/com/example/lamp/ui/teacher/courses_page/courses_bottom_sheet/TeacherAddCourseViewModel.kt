package com.example.lamp.ui.teacher.courses_page.courses_bottom_sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.CourseOnlineDataSourceImpl
import com.example.domain.model.CourseResponseDTO
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TeacherAddCourseViewModel : ViewModel() {
    var courseWebService = ApiManager.getCourseApi()
    var courseOnlineDataSource = CourseOnlineDataSourceImpl(courseWebService)
    var flag = false

    fun AddCourse(courseDTO: CourseResponseDTO) {
        viewModelScope.launch {
            try {
                courseOnlineDataSource.addCourse(courseDTO)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {

                    }
                }

            }
        }
    }
}