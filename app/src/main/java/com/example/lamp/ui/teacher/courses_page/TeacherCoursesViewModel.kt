package com.example.lamp.ui.teacher.courses_page

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.CourseOnlineDataSourceImpl
import com.example.domain.model.CourseResponseDTO
import com.example.domain.repos.data_sources.CourseOnlineDataSource
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class TeacherCoursesViewModel : ViewModel() {
    val courseWebService = ApiManager.getCourseApi()
    val courseOnlineDataSource: CourseOnlineDataSource = CourseOnlineDataSourceImpl(courseWebService)
    val coursesLiveData = MutableLiveData<MutableList<CourseResponseDTO>>()
    val errorMessage = MutableLiveData<String>()
    val deleteCourseLiveData = MutableLiveData<Response<Void>?>()
    private val testService=ApiManager.getFileTransferApi()
    val fileLiveData=MutableLiveData<ResponseBody>()
    var flag = false

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

    fun deleteCourse(id: Int) {
        viewModelScope.launch {
            try {
                deleteCourseLiveData.value = courseOnlineDataSource.deleteCourse(CONSTANTS.courseId)
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