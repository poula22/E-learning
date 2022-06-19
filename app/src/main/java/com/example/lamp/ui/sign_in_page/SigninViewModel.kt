package com.example.lamp.ui.sign_in_page

import androidx.lifecycle.ViewModel
import com.example.data.api.ApiManager
import com.example.data.api.UserWebService

class SigninViewModel:ViewModel() {
    var servie:UserWebService=ApiManager.getUserApi()

}