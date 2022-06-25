package com.example.lamp.ui.sign_in_page

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commonFunctions.CONSTANTS
import com.example.data.api.ApiManager
import com.example.data.api.UserWebService
import com.example.data.model.LoginResponse
import com.example.data.model.UserResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SigninViewModel : ViewModel() {
    var service: UserWebService = ApiManager.getUserApi()
    var liveData = MutableLiveData<UserResponse>()
    var errorMessage = MutableLiveData<String>()
    val sessionManager = CONSTANTS.sessionManager
    var test = MutableLiveData<Boolean>()
    val auth: FirebaseAuth = Firebase.auth
    fun signin(email: String, password: String) {
        try {
            viewModelScope.launch {
                //token = "Bearer ${sessionManager.fetchAuthToken()}"
                //sessionManager?.saveAuthToken(result.authToken)
//                var result = service.logIn(email, password)
                var result = service.logInTest(LoginResponse(email, password))
                liveData.value = result
            }
        } catch (t: Throwable) {
            when (t) {
                is HttpException ->
                    errorMessage.value = t.response()?.errorBody()?.string()
            }
        }

    }

//    fun isVerified() {
//        Log.v("isVerified", auth.currentUser?.isEmailVerified.toString())
//        var result = auth.currentUser?.isEmailVerified!!
//        test.value = result
//    }

    fun loginFirebase(email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
                test.value = task.isSuccessful
            }
    }
}