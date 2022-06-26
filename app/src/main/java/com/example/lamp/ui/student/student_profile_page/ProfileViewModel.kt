package com.example.lamp.ui.student.student_profile_page

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.model.UserResponse
import com.example.domain.model.UserResponseDTO
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProfileViewModel:ViewModel() {
    var liveData=MutableLiveData<UserResponse>()
    var errorMessage=MutableLiveData<String>()
    val userWebService = ApiManager.getUserApi()
    fun getUserInfo(id:Int){
        viewModelScope.launch {
            try {
                liveData.value= userWebService.getUserById(id)
            }catch (ex:Exception){
                Log.v("error",ex.message.toString())
            }catch (t:Throwable){
                when (t){
                    is HttpException ->{
                        errorMessage.value=t.response()?.errorBody()?.string()
                    }else ->{
                        errorMessage.value="successful"
                    }
                }
            }

        }
    }
    fun updateUserInfo(id: Int,user:UserResponseDTO){
        viewModelScope.launch {
            try {
                liveData.value=userWebService.updateUserById(id,user)
            }catch (t:Throwable){
                when (t){
                    is HttpException ->{
                        errorMessage.value=t.response()?.errorBody()?.string()
                    }else ->{
                        errorMessage.value="successful"
                    }
                }
            }

        }
    }
}