package com.example.lamp.ui.parent.parent_home_page.courses_page.parent_grades

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.AssignmentOnlineDataSourceImpl
import com.example.data.repos.data_sources_impl.QuizForGradeOnlineDataSourceImpl
import com.example.domain.model.NewAssignmentResponseDTO
import com.example.domain.model.NewQuizResponseDTO
import com.example.domain.repos.data_sources.AssignmentOnlineDataSource
import com.example.domain.repos.data_sources.QuizForGradeOnlineDataSource
import kotlinx.coroutines.launch

class StudentCourseGradesViewModel : ViewModel() {

    val assignmentsService = ApiManager.getAssignmentsGradesByStudentId()
    val quizzesService = ApiManager.getQuizzesGradesByStudentId()
    val assignmentsLiveData = MutableLiveData<List<NewAssignmentResponseDTO>>()
    val quizzesLiveData = MutableLiveData<List<NewQuizResponseDTO>>()
    val assignmentsDataSource: AssignmentOnlineDataSource =
        AssignmentOnlineDataSourceImpl(assignmentsService)
    val quizzesDataSource: QuizForGradeOnlineDataSource =
        QuizForGradeOnlineDataSourceImpl(quizzesService)

    fun getGradesByStudentId(studentId: Int, courseId: Int) {
        viewModelScope.launch {
            try {
                assignmentsLiveData.value =
                    assignmentsDataSource.getAssignmentGradesByCourseIdByStudentIdForTeacher(
                        courseId,
                        studentId
                    )
                quizzesLiveData.value =
                    quizzesDataSource.getQuizGradesByCourseIdAndStudentIdForTeacher(
                        courseId,
                        studentId
                    )
            } catch (ex: Exception) {
                throw ex
            }
        }
    }


}