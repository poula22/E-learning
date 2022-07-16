package com.example

import android.content.Context
import android.content.SharedPreferences
import com.example.domain.model.UserResponseDTO
import com.example.lamp.R

class SessionManager(context: Context)  {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(context?.getString(R.string.app_name), Context.MODE_PRIVATE)!!
    companion object {
        const val USER_TOKEN = "user_token"
        const val ROLE = "role"
        const val EMAIL = "email"
        const val FIRSTNAME = "first_Name"
        const val LASTNAME = "last_name"
        const val PHONE = "phone"
        const val PROFILEPICTURE = "profile_picture"
        const val ID = "id"
        const val EXPERIESON = "expires_on"

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
    fun saveAuthToken(user: UserResponseDTO) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, user.token)
        editor.putString(ROLE, user.role)
        editor.putString(EMAIL, user.emailAddress)
        editor.putString(FIRSTNAME, user.firstName)
        editor.putString(LASTNAME, user.lastName)
        editor.putString(PHONE, user.phone)
        editor.putString(PROFILEPICTURE, user.profilePic)
        user.id?.let { editor.putInt(ID, it) }
        editor.putString(EXPERIESON, user.expiresOn)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun fetchData():UserResponseDTO{
        return UserResponseDTO(
            prefs.getString(FIRSTNAME, null),
            prefs.getString(LASTNAME, null),
            prefs.getString(EMAIL, null),
            null,
            prefs.getString(ROLE, null),
            prefs.getString(PHONE, null),
            prefs.getString(PROFILEPICTURE, null),
            prefs.getInt(ID, -1),
            expiresOn = prefs.getString(EXPERIESON, null),
            token = prefs.getString(USER_TOKEN, null)
        )
    }

    fun deleteData(){
        prefs.edit().clear().commit()
    }
}