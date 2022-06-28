package com.example.lamp.ui.teacher.students_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.model.StudentResponse
import kotlinx.coroutines.launch

class TeacherStudentsViewModel:ViewModel() {
    var liveData=MutableLiveData<StudentResponse>()
    val webService=ApiManager.getStudentApi()

    fun getTeacherStudents(){
        viewModelScope.launch {
            //getAllStudents()
        }
    }
}