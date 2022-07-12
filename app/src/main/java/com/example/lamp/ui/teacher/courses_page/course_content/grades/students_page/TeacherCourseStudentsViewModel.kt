package com.example.lamp.ui.teacher.courses_page.course_content.grades.students_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.StudentOnlineDataSourceImpl
import com.example.domain.model.StudentResponseDTO
import com.example.domain.repos.data_sources.StudentOnlineDataSource
import kotlinx.coroutines.launch

class TeacherCourseStudentsViewModel : ViewModel() {
    val service = ApiManager.getStudentsByCourseId()
    val liveData = MutableLiveData<List<StudentResponseDTO>>()
    val dataSource: StudentOnlineDataSource = StudentOnlineDataSourceImpl(service)

    fun getStudentsByCourseId(id: Int) {
        viewModelScope.launch {
            try {
                liveData.value = dataSource.getStudentsByCourseId(id)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }


}