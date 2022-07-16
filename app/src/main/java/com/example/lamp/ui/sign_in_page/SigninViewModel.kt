package com.example.lamp.ui.sign_in_page

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_functions.CONSTANTS
import com.example.common_functions.CommonFunctions
import com.example.data.api.ApiManager
import com.example.data.api.UserWebService
import com.example.data.model.UserResponse
import com.example.domain.model.UserResponseDTO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class SigninViewModel : ViewModel() {
    var service: UserWebService = ApiManager.getUserApi()
    var liveData = MutableLiveData<UserResponse>()
    var errorMessage = MutableLiveData<String>()
    val sessionManager = CONSTANTS.sessionManager
    var test = MutableLiveData<Boolean>()
    val auth: FirebaseAuth = Firebase.auth
    fun signin(email: String, password: String) {


                //token = "Bearer ${sessionManager.fetchAuthToken()}"
                //sessionManager?.saveAuthToken(result.authToken)
//                var result = service.logIn(email, password)
                try {
                    service.logInTest(UserResponseDTO(emailAddress = email, password = password)).enqueue(object :Callback<UserResponse>{
                        override fun onResponse(
                            call: Call<UserResponse>,
                            response: Response<UserResponse>
                        ) {
                            if (response.code() == 200) {
                                liveData.postValue(response.body())
                            } else {
                                errorMessage.value = "Invalid email or password"
                            }

                        }

                        override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                            errorMessage.postValue(t.message)
                        }

                    })

                } catch (t: Throwable) {
                    when (t) {
                        is HttpException ->
                            errorMessage.value = t.response()?.errorBody()?.string()
                        else ->{
                            errorMessage.value = t.message
                        }
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