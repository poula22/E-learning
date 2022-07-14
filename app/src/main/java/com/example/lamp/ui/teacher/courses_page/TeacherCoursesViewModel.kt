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

class TeacherCoursesViewModel : ViewModel() {
    var courseWebService = ApiManager.getCourseApi()
    var courseOnlineDataSource: CourseOnlineDataSource = CourseOnlineDataSourceImpl(courseWebService)
    var coursesLiveData = MutableLiveData<MutableList<CourseResponseDTO>>()
    var errorMessage = MutableLiveData<String>()
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


}