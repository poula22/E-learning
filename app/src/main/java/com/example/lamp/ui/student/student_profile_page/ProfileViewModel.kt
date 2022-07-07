package com.example.lamp.ui.student.student_profile_page

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.UserOnlineDataSourceImpl
import com.example.domain.model.UserResponseDTO
import com.example.domain.repos.data_sources.UserOnlineDataSource
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProfileViewModel : ViewModel() {
    var liveData = MutableLiveData<UserResponseDTO>()
    var errorMessage = MutableLiveData<String>()
    val userWebService = ApiManager.getUserApi()
    var userOnlineSource: UserOnlineDataSource = UserOnlineDataSourceImpl(userWebService)
    fun getUserInfo(id: Int) {
        viewModelScope.launch {
            try {
                liveData.value = userOnlineSource.getUserById(id)
            } catch (ex: Exception) {
                Log.v("error", ex.message.toString())
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.response()?.errorBody()?.string()
                    }
                    else -> {
                        errorMessage.value = "successful"
                    }
                }
            }

        }
    }

    fun updateUserInfo(id: Int, user: UserResponseDTO) {
        viewModelScope.launch {
            try {
                liveData.value = userOnlineSource.updateUserById(id, user)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        errorMessage.value = t.response()?.errorBody()?.string()
                    }
                    else -> {
                        errorMessage.value = "successful"
                    }
                }
            }

        }
    }
}