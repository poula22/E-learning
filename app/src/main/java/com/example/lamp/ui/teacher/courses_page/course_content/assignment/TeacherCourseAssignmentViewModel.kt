package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.model.AssignmentResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TeacherCourseAssignmentViewModel: ViewModel(){
    var errorMessage= MutableLiveData<String>()
    var liveData = MutableLiveData<List<AssignmentResponse>>()
    val assignmentWebService= ApiManager.getAssignmentApi()
    fun getAllAssignments(courseId:Int){
        viewModelScope.launch {
            try {
                liveData.value=assignmentWebService.getAssignmentsByCourseId(courseId)
            }catch (t:Throwable){
                when(t){
                    is HttpException ->
                        errorMessage.value=t.response()?.errorBody()?.string()
                }
            }
        }
    }

}