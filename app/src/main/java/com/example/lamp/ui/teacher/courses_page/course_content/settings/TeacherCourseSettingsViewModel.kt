package com.example.lamp.ui.teacher.courses_page.course_content.settings

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.CourseOnlineDataSourceImpl
import com.example.domain.model.CourseResponseDTO
import com.example.domain.repos.data_sources.CourseOnlineDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import kotlin.coroutines.suspendCoroutine

class TeacherCourseSettingsViewModel : ViewModel() {
    private val  service=ApiManager.getCourseApi()
    val liveData= MutableLiveData<CourseResponseDTO>()
    val dropLiveData=MutableLiveData<Response<Void>>()
    private val dataSource:CourseOnlineDataSource=CourseOnlineDataSourceImpl(service,object :CourseOnlineDataSource.CallResult{
        override fun getDTOData(data: CourseResponseDTO) {
            Log.e("dataaaaaa",data.toString())
            getCourseById()
        }

    })
    fun changeCourseImage(image:File){

                    try {
                        val originalFile = image
                        originalFile.name.let { Log.e("file", it) }
                        val filePart= originalFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
                        val file=
                            filePart.let { MultipartBody.Part.createFormData("file", originalFile.name, it) }
                        Log.e("file", file.body.contentType().toString())
                        file.let {
                            dataSource.updateCourseImageByCourseId(CONSTANTS.courseId, it)
//                             .enqueue(
//                             object : Callback<CourseResponseDTO>{
//                                 override fun onResponse(
//                                     call: Call<CourseResponseDTO>,
//                                     response: Response<CourseResponseDTO>
//                                 ) {
//                                     Log.e("response", response.body().toString())
//                                     fileLiveData.value=response.body()
//                                 }
//
//                                 override fun onFailure(call: Call<CourseResponseDTO>, t: Throwable) {
//                                     Log.e("error", "error 404 404 404 404 404 404 404")
//                                 }
//
//
//                             }
//                         )
                        }
                    }catch (t:Throwable){
                        throw t
                    }


    }

    fun getCourseById(){
        viewModelScope.launch {
            try {
                liveData.value=dataSource.getCourse(CONSTANTS.courseId)
            }catch (t:Throwable){
                t.printStackTrace()
            }
        }
    }
//    var callResult:CallResult?=null
//    interface CallResult{
//        fun getDTOData(data: CourseResponseDTO)
//    }


    fun dropCourse(){
        viewModelScope.launch {
            dropLiveData.value=dataSource.dropCourse(CONSTANTS.courseId,CONSTANTS.user_id)
        }
    }
}