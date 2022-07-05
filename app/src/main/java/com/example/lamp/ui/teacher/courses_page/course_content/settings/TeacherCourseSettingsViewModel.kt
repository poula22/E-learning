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
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import kotlin.concurrent.thread
import kotlin.coroutines.suspendCoroutine

class TeacherCourseSettingsViewModel : ViewModel() {
    private val  service=ApiManager.getCourseApi()
    val liveData= MutableLiveData<CourseResponseDTO>()
    val dropLiveData=MutableLiveData<Response<Void>>()
    val fileLiveData=MutableLiveData<CourseResponseDTO>()
    val testLiveData=MutableLiveData<ResponseBody>()
    val testService=ApiManager.getFileTransferApi()
    private val dataSource:CourseOnlineDataSource=CourseOnlineDataSourceImpl(service)
    fun changeCourseImage(image:File){

        runBlocking {
            try {
//                val originalFile = image
//                originalFile.name.let { Log.e("file", it) }
//                val filePart= originalFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
//                val file=
//                    filePart.let { MultipartBody.Part.createFormData("file", originalFile.name, it) }
//                Log.e("file", file.body.contentType().toString())
//                file.let {
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
//                }

                val requestBody: RequestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", image.name,image.asRequestBody("image/jpeg".toMediaTypeOrNull()))
                    .build()
                fileLiveData.value=dataSource.updateCourseImageByCourseId(CONSTANTS.courseId, requestBody)


            }catch (t:Throwable){
                throw t
            }

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

    fun getImage(){
        viewModelScope.launch {
            try {
                testLiveData.value= testService.test()
            }catch (t:Throwable){
                t.printStackTrace()
            }
        }
    }


}