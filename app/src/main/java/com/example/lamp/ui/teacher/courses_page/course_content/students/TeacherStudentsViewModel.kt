package com.example.lamp.ui.teacher.courses_page.course_content.students

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.StudentOnlineDataSourceImpl
import com.example.domain.model.StudentResponseDTO
import kotlinx.coroutines.launch

class TeacherStudentsViewModel : ViewModel() {
    var liveData = MutableLiveData<List<StudentResponseDTO>>()
    val webService = ApiManager.getStudentApi()
    val studentOnlineDataSource = StudentOnlineDataSourceImpl(webService)

    fun getTeacherCourseStudents() {
        viewModelScope.launch {
            //getAllStudentsByCourseId(courseId)
            viewModelScope.launch {
                try {
                    liveData.value =
                        studentOnlineDataSource.getStudentsByCourseId(
                            CONSTANTS.courseId
                        )
                } catch (ex: Exception) {
                    throw ex
                }
            }

        }
    }
}