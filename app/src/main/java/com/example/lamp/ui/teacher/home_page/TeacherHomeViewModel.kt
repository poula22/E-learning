package com.example.lamp.ui.teacher.home_page

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

class TeacherHomeViewModel : ViewModel() {
    var liveData=MutableLiveData<MutableList<TodoDTO>>()
    var offlineDataSource:TodoOfflineDataSource=TodoOfflineDataSourceImp(DataBase.getInstance(),0)
    var repository:TodoRepository=TodoRepositoryImp(offlineDataSource)

    fun getData(){
        viewModelScope.launch {
            repository.getAllTodo().let {
                liveData.value =it
            }
        }
    }
}