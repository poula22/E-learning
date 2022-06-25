package com.example.lamp.ui.sign_in_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commonFunctions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.api.UserWebService
import com.example.data.model.UserResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SigninViewModel:ViewModel() {
    var service:UserWebService=ApiManager.getUserApi()
    var liveData=MutableLiveData<UserResponse>()
    var errorMessage=MutableLiveData<String>()
    val sessionManager=CONSTANTS.sessionManager
    fun signin(email:String, password:String){
        try {
            viewModelScope.launch {
                //token = "Bearer ${sessionManager.fetchAuthToken()}"
                //sessionManager?.saveAuthToken(result.authToken)
                var result=service.logIn(email,password)
                liveData.value=result
            }
        }catch (t:Throwable){
            when(t){
                is HttpException ->
                    errorMessage.value=t.response()?.errorBody()?.string()
            }
        }

    }
}