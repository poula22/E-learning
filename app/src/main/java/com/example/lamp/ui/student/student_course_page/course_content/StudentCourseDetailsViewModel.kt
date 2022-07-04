package com.example.lamp.ui.student.student_course_page.course_content

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.CourseOnlineDataSourceImpl
import com.example.domain.repos.data_sources.CourseOnlineDataSource
import kotlinx.coroutines.launch
import retrofit2.Response

class StudentCourseDetailsViewModel:ViewModel() {
    private val service=ApiManager.getCourseApi()
    private val dataSource:CourseOnlineDataSource=CourseOnlineDataSourceImpl(service)
    val liveData=MutableLiveData<Response<Void>>()

    fun dropCourse(){
        viewModelScope.launch {
            liveData.value=dataSource.dropCourse(CONSTANTS.courseId,CONSTANTS.user_id)
        }
    }
}