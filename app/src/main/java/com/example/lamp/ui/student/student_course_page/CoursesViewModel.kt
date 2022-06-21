package com.example.lamp.ui.student.student_course_page

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.model.CourseResponse
import com.example.domain.model.CourseResponseDTO
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CoursesViewModel:ViewModel() {
    var courseWebService = ApiManager.getCourseApi()
    var coursesLiveData=MutableLiveData<MutableList<CourseResponse>>()

    fun joinCourse(studentID:Int,courseCode:Int) {
        viewModelScope.launch {
            try {
                val course = courseWebService.joinCourse(studentID, courseCode)
                coursesLiveData.value?.add(course)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
//                        Toast.makeText(,t.response()?.errorBody().toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }
    }

    fun getAllCourses() {
        viewModelScope.launch {
            try {
                val courses = courseWebService.getAllCourses()
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