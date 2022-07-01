package com.example.lamp.ui.student.student_course_page.course_content.assignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.model.AssignmentDetailsResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

//filter
class StudentCourseAssignmentViewModel:ViewModel() {
    var errorMessage=MutableLiveData<String>()
    var liveData =MutableLiveData<List<AssignmentDetailsResponse>>()
    val assignmentWebService=ApiManager.getAssignmentApi()
    fun getData(courseId:Int){
        viewModelScope.launch {
            try {
                liveData.value=assignmentWebService.getAssignmentsByCourseIdForStudent(courseId,CONSTANTS.user_id)

            }catch (t:Throwable){
                when(t){
                    is HttpException ->
                        errorMessage.value=t.response()?.errorBody()?.string()
                }
            }
        }
    }
    fun filterList(text: String):MutableList<AssignmentDetailsResponse> {
        val filteredList = mutableListOf<AssignmentDetailsResponse>()
        liveData.value?.let {
            for (item in it) {
                if(item.submitted==true && text.lowercase()=="submitted"){
                    filteredList.add(item) //add to filtered list
                }else if (item.submitted==false && text.lowercase()=="not submitted") {
                    filteredList.add(item) //add to filtered list
                }else if(text.lowercase() == "all" ){
                    filteredList.add(item) // add all the items that are not filtered
                }
            }

        }
        return filteredList
    }
}