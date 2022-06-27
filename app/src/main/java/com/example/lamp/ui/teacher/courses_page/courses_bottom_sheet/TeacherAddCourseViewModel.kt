package com.example.lamp.ui.teacher.courses_page.courses_bottom_sheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.model.CourseResponse
import com.example.domain.model.CourseResponseDTO
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TeacherAddCourseViewModel:ViewModel() {
    var courseWebService = ApiManager.getCourseApi()
    var flag=false

    fun AddCourse(courseDTO: CourseResponseDTO){
        viewModelScope.launch {
            try {
                courseWebService.addCourse(courseDTO)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {

                    }
                }

            }
        }
    }
}