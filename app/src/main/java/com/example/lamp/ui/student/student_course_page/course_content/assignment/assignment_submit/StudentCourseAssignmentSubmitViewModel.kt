package com.example.lamp.ui.student.student_course_page.course_content.assignment.assignment_submit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.AssignmentAnswerOnlineDataSourceImpl
import com.example.domain.model.AssignmentAnswerResponseDTO
import kotlinx.coroutines.launch

class StudentCourseAssignmentSubmitViewModel : ViewModel() {
    val assignmentAnswerWebService = ApiManager.getAssignmentAnswerApi()
    val assignmentAnswerOnlineDataSource = AssignmentAnswerOnlineDataSourceImpl(assignmentAnswerWebService)
    fun submitAssignment(assignmentAnswer: AssignmentAnswerResponseDTO) {
        viewModelScope.launch {
            var test = assignmentAnswerOnlineDataSource.addAssignmentAnswer(assignmentAnswer)
            Log.v("test", test.toString())
        }
    }
}