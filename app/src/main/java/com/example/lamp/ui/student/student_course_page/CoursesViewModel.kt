package com.example.lamp.ui.student.student_course_page

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.model.CourseResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class CoursesViewModel : ViewModel() {
    var courseWebService = ApiManager.getCourseApi()
    var coursesLiveData = MutableLiveData<MutableList<CourseResponse>>()
    var flag = false
    var course = MutableLiveData<Response<Void>?>()
    var errorMessage = MutableLiveData<String>()

    fun deleteCourse(id: Int) {
        viewModelScope.launch {
            try {
                course.value = courseWebService.dropCourse(id, CONSTANTS.user_id)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.message
                    }
                    else -> {
                        Log.e("Error", t.message.toString())
                    }
                }
            }
        }
    }

    fun joinCourse(studentID: Int, courseCode: Int) {
        flag = true
        viewModelScope.launch {
            try {
                course.value = courseWebService.joinCourse(courseCode, studentID)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.message
                    }
                    else -> {
                        Log.e("Error", t.message.toString())
                    }
                }
            }

        }
    }

    fun getAllCourses() {
        viewModelScope.launch {
            try {
                val courses = courseWebService.getCoursesByStudentId(CONSTANTS.user_id)
                coursesLiveData.value = courses.toMutableList()
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.message
                    }
                    else -> {
                        Log.e("Error", t.message.toString())
                    }

                }
            }

        }


    }
}