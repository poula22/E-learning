package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.AssignmentOnlineDataSourceImpl
import com.example.domain.model.AssignmentResponseDTO
import kotlinx.coroutines.launch

class TeacherCourseAddAssignmentViewModel : ViewModel() {
    val service = ApiManager.getAssignmentApi()
    val assignmentOnlineDataSource = AssignmentOnlineDataSourceImpl(service)
    var liveData = MutableLiveData<AssignmentResponseDTO>()

    fun addAssignment(assignment: AssignmentResponseDTO) {
        viewModelScope.launch {
            try {
                liveData.value = assignmentOnlineDataSource.addAssignment(assignment)
            } catch (ex: Exception) {
                throw ex
            }

        }
    }
}