package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.AssignmentOnlineDataSourceImpl
import com.example.domain.model.AssignmentResponseDTO
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TeacherCourseAssignmentViewModel : ViewModel() {
    var errorMessage = MutableLiveData<String>()
    var liveData = MutableLiveData<List<AssignmentResponseDTO>>()
    val assignmentWebService = ApiManager.getAssignmentApi()
    val assignmentOnlineDataSource = AssignmentOnlineDataSourceImpl(assignmentWebService)
    fun getAllAssignments(courseId: Int) {
        viewModelScope.launch {
            try {
                liveData.value = assignmentOnlineDataSource.getAssignmentsByCourseId(courseId)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException ->
                        errorMessage.value = t.response()?.errorBody()?.string()
                }
            }
        }
    }

}