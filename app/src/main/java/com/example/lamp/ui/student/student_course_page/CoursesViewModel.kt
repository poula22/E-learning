package com.example.lamp.ui.student.student_course_page

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.CourseOnlineDataSourceImpl
import com.example.domain.model.CourseResponseDTO
import com.example.domain.repos.data_sources.CourseOnlineDataSource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class CoursesViewModel : ViewModel() {
    var courseWebService = ApiManager.getCourseApi()
    var courseOnlineDataSource: CourseOnlineDataSource = CourseOnlineDataSourceImpl(courseWebService)
    var coursesLiveData = MutableLiveData<MutableList<CourseResponseDTO>>()
    var flag = false
    var course = MutableLiveData<Response<Void>?>()
    var errorMessage = MutableLiveData<String>()
    private val testService=ApiManager.getFileTransferApi()
    val fileLiveData=MutableLiveData<ResponseBody>()


    fun getImage(fileName:String){
        testService.getImageCall(fileName).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                t.message?.let { Log.d("error", it) }
            }
            override fun onResponse(call: retrofit2.Call<ResponseBody>, response: retrofit2.Response<ResponseBody>) {
                fileLiveData.postValue(response.body())
            }
        })
    }

    fun deleteCourse(id: Int) {
        viewModelScope.launch {
            try {
                course.value = courseOnlineDataSource.dropCourse(id, CONSTANTS.user_id)
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
                course.value = courseOnlineDataSource.joinCourse(courseCode, studentID)
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
                val courses = courseOnlineDataSource.getCoursesByStudentId(CONSTANTS.user_id)
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