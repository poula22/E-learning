package com.example.lamp.ui.teacher.courses_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.CourseOnlineDataSourceImpl
import com.example.domain.model.CourseResponseDTO
import com.example.domain.repos.data_sources.CourseOnlineDataSource
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TeacherCoursesViewModel : ViewModel() {
    var courseWebService = ApiManager.getCourseApi()
    var courseOnlineDataSource: CourseOnlineDataSource = CourseOnlineDataSourceImpl(courseWebService)
    var coursesLiveData = MutableLiveData<MutableList<CourseResponseDTO>>()
    var flag = false

    fun getAllCourses() {
        viewModelScope.launch {
            try {
                val courses = courseOnlineDataSource.getCoursesByTeacherId(CONSTANTS.user_id)
                coursesLiveData.value = courses.toMutableList()
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {

                    }
                }

            }
        }

    }


}