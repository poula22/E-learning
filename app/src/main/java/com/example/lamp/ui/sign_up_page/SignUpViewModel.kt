package com.example.lamp.ui.sign_up_page

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.api.StudentWebService
import com.example.data.api.TeacherWebService
import com.example.data.model.TeacherResponse
import com.example.domain.model.TeacherResponseDTO
import kotlinx.coroutines.launch
import retrofit2.HttpException


class SignUpViewModel : ViewModel() {
    var studentService: StudentWebService = ApiManager.getStudentApi()
    var teacherService: TeacherWebService = ApiManager.getTeacherApi()
    var parentService: StudentWebService = ApiManager.getStudentApi()
    var liveData = MutableLiveData<TeacherResponse>()

    fun addUser() {
        viewModelScope.launch {
            try {
                liveData.value = teacherService.addTeacher(
                    TeacherResponseDTO(
                        "test2",
                        "test2",
                        "test10@gmail.com",
                        "test2",
                        "Teacher",
                        "+201233333335",
                        ""
                    )
                )
            } catch (cause: Throwable) {
                when (cause) {
                    is HttpException -> cause.response()?.errorBody()?.string()
                        ?.let { Log.e("error", it) }
                    else -> {


                    }
                }
            }

        }

    }
}
