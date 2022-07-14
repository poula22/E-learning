package com.example.lamp.ui.parent.parent_home_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.CourseOnlineDataSourceImpl
import com.example.data.repos.data_sources_impl.ParentStudentOnlineDatSourceImpl
import com.example.data.repos.data_sources_impl.StudentOnlineDataSourceImpl
import com.example.domain.model.CourseResponseDTO
import com.example.domain.model.ParentStudentRequestDTO
import com.example.domain.model.ParentStudentResponseDTO
import com.example.domain.model.StudentResponseDTO
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {
    var service = ApiManager.getParentStudent()
    var parentService = ApiManager.getStudentApi()
    var dataSource = ParentStudentOnlineDatSourceImpl(service)
    var parentDataSource = StudentOnlineDataSourceImpl(parentService)
    var liveDate = MutableLiveData<ParentStudentResponseDTO>()
    var parentLiveData = MutableLiveData<List<StudentResponseDTO>>()


    fun verifyStudentRequest(student: ParentStudentRequestDTO) {
        viewModelScope.launch {
            try {
                val response = dataSource.verifyStudentRequest(student)
                liveDate.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun getStudentsByParentId(parentId: Int): List<StudentResponseDTO> {
        viewModelScope.launch {
            try {
                val response = parentDataSource.getStudentsByParentId(parentId)
                parentLiveData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return parentLiveData.value!!
    }





}