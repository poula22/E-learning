package com.example.lamp.ui.student.student_course_page

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commonFunctions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.model.CourseResponse
import com.example.domain.model.CourseResponseDTO
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CoursesViewModel:ViewModel() {
    var courseWebService = ApiManager.getCourseApi()
    var coursesLiveData=MutableLiveData<MutableList<CourseResponse>>()
    var flag=false
    fun joinCourse(studentID:Int,courseCode:Int) {
        flag=true
        viewModelScope.launch {
            try {
                courseWebService.joinCourse(courseCode,studentID)
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