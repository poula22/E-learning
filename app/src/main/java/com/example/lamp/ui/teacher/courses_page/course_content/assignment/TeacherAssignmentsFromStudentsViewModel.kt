package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.AssignmentAnswerOnlineDataSourceImpl
import com.example.domain.model.AssignmentAnswerDetailsResponseDTO
import com.example.domain.model.AssignmentAnswerResponseDTO
import com.example.domain.model.AssignmentResponseDTO
import com.example.domain.repos.data_sources.AssignmentAnswerOnlineDataSource
import kotlinx.coroutines.launch

class TeacherAssignmentsFromStudentsViewModel:ViewModel() {
    val service=ApiManager.getAssignmentAnswerApi()
    var liveData=MutableLiveData<List<AssignmentAnswerDetailsResponseDTO>>()
    private val dataSource:AssignmentAnswerOnlineDataSource=AssignmentAnswerOnlineDataSourceImpl(service)

    fun getAllAssignmentAnswers(assignmentId:Int){
        viewModelScope.launch {
            try{
                liveData.value=dataSource.getAssignmentAnswersByAssignmentId(assignmentId)
            }catch (ex:Exception){
                throw ex

            }
        }
    }

    fun updateAssignmentAnswer(assignmentId:Int,assignmentResponseDTO: AssignmentAnswerResponseDTO){
        viewModelScope.launch {
            try{
                service.updateAssignmentAnswer(assignmentId,assignmentResponseDTO)
            }catch (ex:Exception){
                liveData.value=null

            }
        }
    }
    

}