package com.example.lamp.ui.student.student_course_page

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commonFunctions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.model.CourseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

class CoursesViewModel:ViewModel() {
    var courseWebService = ApiManager.getCourseApi()
    var coursesLiveData=MutableLiveData<MutableList<CourseResponse>>()
    var flag=false
    var course=MutableLiveData<Response<*>>()

    fun deleteCourse(id:Int){
        viewModelScope.launch {
            courseWebService.dropCourse(id,CONSTANTS.user_id)
        }
    }
    fun joinCourse(studentID:Int,courseCode:Int) {
        flag=true
        viewModelScope.launch {
            try {
                course.value=courseWebService.joinCourse(courseCode, studentID).body() as Response<*>
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