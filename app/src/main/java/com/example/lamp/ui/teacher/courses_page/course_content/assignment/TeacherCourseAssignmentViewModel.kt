package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.AssignmentOnlineDataSourceImpl
import com.example.domain.model.AssignmentResponseDTO
import com.example.domain.repos.data_sources.AssignmentOnlineDataSource
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class TeacherCourseAssignmentViewModel : ViewModel() {
    var errorMessage = MutableLiveData<String>()
    val liveData = MutableLiveData<List<AssignmentResponseDTO>>()
    val deleteLiveData=MutableLiveData<Response<Void>>()
    private val assignmentWebService = ApiManager.getAssignmentApi()
    private val assignmentOnlineDataSource: AssignmentOnlineDataSource = AssignmentOnlineDataSourceImpl(assignmentWebService)
    fun getAllAssignments() {
        viewModelScope.launch {
            try {
                liveData.value = assignmentOnlineDataSource.getAssignmentsByCourseId(CONSTANTS.courseId)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException ->
                        errorMessage.value = t.response()?.errorBody()?.string()
                }
            }
        }
    }

    fun removeAssignment(assignment: AssignmentResponseDTO?) {
        viewModelScope.launch {
            try {
                deleteLiveData.value = assignment?.id?.let {
                    assignmentOnlineDataSource.deleteAssignment(
                        it
                    )
                }
            } catch (t: Throwable) {
                when (t) {
                    is HttpException ->
                        errorMessage.value = t.response()?.errorBody()?.string()
                }
            }
        }
    }

}