package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.AssignmentOnlineDataSourceImpl
import com.example.domain.model.AssignmentResponseDTO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.File

class TeacherCourseAddAssignmentViewModel : ViewModel() {
    val service = ApiManager.getAssignmentApi()
    val errorMessage=MutableLiveData<String>()
    val assignmentOnlineDataSource = AssignmentOnlineDataSourceImpl(service)
    var liveData = MutableLiveData<Response<Void>>()

    fun addAssignment(assignment: AssignmentResponseDTO, file: File?) {
        runBlocking{
            try {
                val part =MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("title",assignment.title?:"")
                    .addFormDataPart("description",assignment.description?:"")
                    .addFormDataPart("startDate",assignment.startDate?:"")
                    .addFormDataPart("endTime",assignment.endTime?:"")
                    .addFormDataPart("totalPoints",assignment.grade.toString())
                    .addFormDataPart("courseId",assignment.courseId.toString())
                    if (file!=null) {
                        part.addFormDataPart("filePath",file.name,file.asRequestBody("application/pdf".toMediaTypeOrNull()))
                    }
                    val body:RequestBody=part.build()
                liveData.value = assignmentOnlineDataSource.addAssignment(body)
            } catch (t: Throwable) {
                when(t){
                    is HttpException ->{
                        errorMessage.value=t.response()?.errorBody()?.string()
                     }
                    else -> {}
                }
            }
        }
    }
}