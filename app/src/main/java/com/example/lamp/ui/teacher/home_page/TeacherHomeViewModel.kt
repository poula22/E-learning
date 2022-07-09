package com.example.lamp.ui.teacher.home_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.database.DataBase
import com.example.data.repos.TodoRepositoryImp
import com.example.data.repos.data_sources_impl.CourseOnlineDataSourceImpl
import com.example.data.repos.data_sources_impl.TodoOfflineDataSourceImp
import com.example.domain.model.CourseResponseDTO
import com.example.domain.model.TodoDTO
import com.example.domain.repos.TodoRepository
import com.example.domain.repos.data_sources.CourseOnlineDataSource
import com.example.domain.repos.data_sources.TodoOfflineDataSource
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.*

class TeacherHomeViewModel : ViewModel() {
    val liveData = MutableLiveData<MutableList<TodoDTO>>()
    val removeLiveData = MutableLiveData<TodoDTO>()
    private var offlineDataSource: TodoOfflineDataSource =
        TodoOfflineDataSourceImp(DataBase.getInstance(), 0)
    private val todoRepository: TodoRepository = TodoRepositoryImp(offlineDataSource)
    private val coursesDataSource:CourseOnlineDataSource= CourseOnlineDataSourceImpl(ApiManager.getCourseApi())
    val coursesLiveData = MutableLiveData<List<CourseResponseDTO>>()
    val errorMessage = MutableLiveData<String>()
    fun getData() {
        viewModelScope.launch {
            todoRepository.getAllTodo().let {
                liveData.value = it
            }
        }
    }

    fun getCourses(){
        viewModelScope.launch {
            try {
                coursesLiveData.value = coursesDataSource.getCoursesByTeacherId(CONSTANTS.user_id)
            }  catch (t: Throwable) {
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

    fun getTodoByDate(time: Date) {
        viewModelScope.launch {
            try {
                liveData.value = todoRepository.getTodoByDate(time)
            }  catch (t: Throwable) {
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

    fun removeTodo(todo: TodoDTO) {
        viewModelScope.launch {
            try {
                removeLiveData.value = todoRepository.removeTodo(todo)
            }  catch (t: Throwable) {
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
}