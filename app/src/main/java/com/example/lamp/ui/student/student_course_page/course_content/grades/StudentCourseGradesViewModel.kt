package com.example.lamp.ui.student.student_course_page.course_content.grades

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.AssignmentOnlineDataSourceImpl
import com.example.data.repos.data_sources_impl.QuizOnlineDataSourceImpl
import com.example.domain.model.AssignmentDetailsResponseDTO
import com.example.domain.model.AssignmentResponseDTO
import com.example.domain.model.QuizResponseDTO
import com.example.domain.repos.data_sources.AssignmentOnlineDataSource
import com.example.domain.repos.data_sources.QuizOnlineDataSource
import kotlinx.coroutines.launch

class StudentCourseGradesViewModel : ViewModel() {

    val assignmentsService = ApiManager.getAssignmentsGradesByStudentId()
    val quizzesService = ApiManager.getQuizzesGradesByStudentId()
    val assignmentsLiveData = MutableLiveData<List<AssignmentDetailsResponseDTO>>()
    val quizzesLiveData = MutableLiveData<List<QuizResponseDTO>>()
    val assignmentsDataSource: AssignmentOnlineDataSource =
        AssignmentOnlineDataSourceImpl(assignmentsService)
    val quizzesDataSource: QuizOnlineDataSource = QuizOnlineDataSourceImpl(quizzesService)

    fun getGradesByStudentId() {
        viewModelScope.launch {
            try {
                assignmentsLiveData.value =
                    assignmentsDataSource.getAssignmentsByCourseIdForStudent(
                        CONSTANTS.courseId,
                        CONSTANTS.user_id
                    )
                quizzesLiveData.value =
                    quizzesDataSource.getQuizzesByCourseId(
                        CONSTANTS.courseId
                    )
            } catch (ex: Exception) {
                throw ex
            }
        }
    }


}