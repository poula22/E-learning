package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.model.AssignmentResponse
import com.example.domain.model.AssignmentResponseDTO
import kotlinx.coroutines.launch

class TeacherCourseAddAssignmentViewModel:ViewModel() {
    val service=ApiManager.getAssignmentApi()
    var liveData=MutableLiveData<AssignmentResponse>()

    fun addAssignment(assignment:AssignmentResponseDTO){
        viewModelScope.launch {
            try {
                liveData.value=service.addAssignment(assignment)
            }catch (ex:Exception){
                throw ex
            }

        }
    }
}