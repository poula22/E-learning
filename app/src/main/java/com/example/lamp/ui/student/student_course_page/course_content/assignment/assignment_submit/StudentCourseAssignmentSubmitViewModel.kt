package com.example.lamp.ui.student.student_course_page.course_content.assignment.assignment_submit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.api.AssignmentAnswerWebService
import com.example.domain.model.AssignmentAnswerResponseDTO
import kotlinx.coroutines.launch

class StudentCourseAssignmentSubmitViewModel:ViewModel() {
    val assignmentAnswerWebService = ApiManager.getAssignmentAnswerApi()
    fun submitAssignment(assignmentAnswer:AssignmentAnswerResponseDTO) {
        viewModelScope.launch {
            var test=assignmentAnswerWebService.addAssignmentAnswer(assignmentAnswer)
            Log.v("test",test.toString())
        }
    }
}