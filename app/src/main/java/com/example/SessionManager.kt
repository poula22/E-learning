package com.example

import android.content.Context
import android.content.SharedPreferences
import com.example.lamp.R

class SessionManager(context: Context)  {
    private var prefs: SharedPreferences =
        context?.getSharedPreferences(context?.getString(R.string.app_name), Context.MODE_PRIVATE)!!
    companion object {
        const val USER_TOKEN = "user_token"
        private var sessionManager: SessionManager? = null
        fun getInstance(context: Context): SessionManager {
            if (sessionManager == null) {
                sessionManager = SessionManager(context)
            }
            return sessionManager as SessionManager
        }
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}