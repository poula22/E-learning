package com.example.lamp.ui.student.student_course_page.course_content.assignment.assignment_submit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.AssignmentAnswerOnlineDataSourceImpl
import com.example.domain.model.AssignmentAnswerResponseDTO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class StudentCourseAssignmentSubmitViewModel : ViewModel() {
    val assignmentAnswerWebService = ApiManager.getAssignmentAnswerApi()
    val assignmentAnswerOnlineDataSource = AssignmentAnswerOnlineDataSourceImpl(assignmentAnswerWebService)
    val liveData=MutableLiveData<AssignmentAnswerResponseDTO>()
    fun submitAssignment(assignmentAnswer: AssignmentAnswerResponseDTO,file: File) {
        runBlocking{
            val requestBody: RequestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("submitDate",assignmentAnswer.submitDate?:"2022-07-05T15:42:32.723Z")
                .addFormDataPart("studentId",CONSTANTS.user_id.toString())
                .addFormDataPart("assignmentId",assignmentAnswer.assignmentId.toString())
                .addFormDataPart("pdf",file.name,file.asRequestBody("application/pdf".toMediaTypeOrNull()))
                .build()
            liveData.value= assignmentAnswerOnlineDataSource.addAssignmentAnswer(requestBody)
        }
    }
}