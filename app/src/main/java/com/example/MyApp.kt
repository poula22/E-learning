package com.example

import android.app.Application
import com.example.commonFunctions.CONSTANTS
import com.example.data.database.DataBase

class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        DataBase.init(this)
        CONSTANTS.sessionManager=SessionManager.getInstance(this)
    }
}