package com.example.lamp.ui.student.student_course_page.course_content.assignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.AssignmentOnlineDataSourceImpl
import com.example.domain.model.AssignmentAnswerResponseDTO
import com.example.domain.model.AssignmentDetailsResponseDTO
import com.example.domain.repos.data_sources.AssignmentOnlineDataSource
import kotlinx.coroutines.launch
import retrofit2.HttpException

//filter
class StudentCourseAssignmentViewModel : ViewModel() {
    var errorMessage = MutableLiveData<String>()
    var liveData = MutableLiveData<List<AssignmentDetailsResponseDTO>>()
    val assignmentWebService = ApiManager.getAssignmentApi()
    val assignmentOnlineDataSource: AssignmentOnlineDataSource = AssignmentOnlineDataSourceImpl(assignmentWebService)
    fun getData(courseId: Int) {
        viewModelScope.launch {
            try {
                liveData.value =
                    assignmentOnlineDataSource.getAssignmentsByCourseIdForStudent(courseId, CONSTANTS.user_id)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException ->
                        errorMessage.value = t.response()?.errorBody()?.string()
                }
            }
        }
    }

    fun filterList(text: String): MutableList<AssignmentDetailsResponseDTO> {
        val filteredList = mutableListOf<AssignmentDetailsResponseDTO>()
        liveData.value?.let {
            for (item in it) {
                if (item.submitted == true && text.lowercase() == "submitted") {
                    filteredList.add(item) //add to filtered list
                } else if (item.submitted == false && text.lowercase() == "not submitted") {
                    filteredList.add(item) //add to filtered list
                } else if (text.lowercase() == "all") {
                    filteredList.add(item) // add all the items that are not filtered
                }
            }

        }
        return filteredList
    }


}