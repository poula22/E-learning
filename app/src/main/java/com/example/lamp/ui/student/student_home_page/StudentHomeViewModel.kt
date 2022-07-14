package com.example.lamp.ui.student.student_home_page

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.database.DataBase
import com.example.data.repos.TodoRepositoryImp
import com.example.data.repos.data_sources_impl.CourseOnlineDataSourceImpl
import com.example.data.repos.data_sources_impl.ParentStudentOnlineDatSourceImpl
import com.example.data.repos.data_sources_impl.TodoOfflineDataSourceImp
import com.example.domain.model.CourseResponseDTO
import com.example.domain.model.ParentStudentResponseDTO
import com.example.domain.model.TodoDTO
import com.example.domain.repos.TodoRepository
import com.example.domain.repos.data_sources.CourseOnlineDataSource
import com.example.domain.repos.data_sources.TodoOfflineDataSource
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class StudentHomeViewModel : ViewModel() {
    var liveData = MutableLiveData<MutableList<TodoDTO>>()
    var offlineDataSource: TodoOfflineDataSource =
        TodoOfflineDataSourceImp(DataBase.getInstance(), 1)
    var repository: TodoRepository = TodoRepositoryImp(offlineDataSource)
    private val coursesDataSource: CourseOnlineDataSource =
        CourseOnlineDataSourceImpl(ApiManager.getCourseApi())
    val coursesLiveData = MutableLiveData<List<CourseResponseDTO>>()
    val errorMessage = MutableLiveData<String>()
    val testLiveData = MutableLiveData<ResponseBody>()
    private val testService = ApiManager.getFileTransferApi()
    var flag = false
    var course = MutableLiveData<Response<Void>?>()


    var parentService = ApiManager.getParentStudent()
    var parentDataSource = ParentStudentOnlineDatSourceImpl(parentService)
    var parentLiveDateList = MutableLiveData<List<ParentStudentResponseDTO>>()
    var parentLiveDate = MutableLiveData<ParentStudentResponseDTO>()


    fun getData() {
        viewModelScope.launch {
            repository.getAllTodo().let {
                liveData.value = it
            }
        }
    }

    fun joinCourse(courseCode: Int) {
        flag = true
        viewModelScope.launch {
            try {
                course.value = coursesDataSource.joinCourse(courseCode, CONSTANTS.user_id)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.message
                    }
                    else -> {
                        Log.e("Error", t.message.toString())
                    }
                }
            }

        }
    }

    fun getCourses() {
        viewModelScope.launch {
            try {
                coursesLiveData.value = coursesDataSource.getCoursesByStudentId(CONSTANTS.user_id)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.response()?.errorBody()?.string()
                    }
                    else -> {
                        errorMessage.value = "failed"
                    }
                }
            }

        }
    }

    fun getImage() {
        viewModelScope.launch {
            try {
                testLiveData.value = testService.getImage(CONSTANTS.profilePic)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }


    fun getUnverifiedParents(studentId: Int) {
        viewModelScope.launch {
            try {
                parentLiveDateList.value =
                    parentDataSource.getUnVerifiedParentStudentRequests(studentId)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }


    fun rejectParentRequest(parentId: Int, studentId: Int) {
        viewModelScope.launch {
            try {
                parentLiveDate.value =
                    parentDataSource.dropParentStudentRequest(parentId, studentId)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    fun acceptParentRequest(parentId: Int, studentId: Int) {
        viewModelScope.launch {
            try {
                parentLiveDate.value =
                    parentDataSource.verifyParentStudentRequest(parentId, studentId)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }


}

