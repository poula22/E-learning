package com.example.lamp.ui.teacher.courses_page.course_content.dashboard

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.database.DataBase
import com.example.data.repos.TodoOfflineDataSourceImp
import com.example.data.repos.TodoRepositoryImp
import com.example.domain.model.TodoDTO
import com.example.domain.repos.TodoOfflineDataSource
import com.example.domain.repos.TodoRepository
import kotlinx.coroutines.launch

class TeacherCourseDashboardViewModel : ViewModel() {
    var liveData=MutableLiveData<MutableList<TodoDTO>>()
    var offlineDataSource:TodoOfflineDataSource=TodoOfflineDataSourceImp(DataBase.getInstance())
    var repository:TodoRepository=TodoRepositoryImp(offlineDataSource)

    fun getData(){
        viewModelScope.launch {
            repository.getAllTodo().let {
                liveData.value =it
            }
        }
    }
}