package com.example.lamp.ui.teacher.courses_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.model.CourseResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TeacherCoursesViewModel:ViewModel() {
    var courseWebService = ApiManager.getCourseApi()
    var coursesLiveData= MutableLiveData<MutableList<CourseResponse>>()
    var flag=false

    fun getAllCourses() {
        viewModelScope.launch {
            try {
                val courses = courseWebService.getCoursesByStudentId(CONSTANTS.user_id)
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