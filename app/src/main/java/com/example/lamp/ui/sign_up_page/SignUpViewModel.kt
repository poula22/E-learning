package com.example.lamp.ui.sign_up_page

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.api.ParentWebService
import com.example.data.api.StudentWebService
import com.example.data.api.TeacherWebService
import com.example.data.model.TeacherResponse
import com.example.data.model.UserResponse
import com.example.domain.model.ParentResponseDTO
import com.example.domain.model.StudentResponseDTO
import com.example.domain.model.TeacherResponseDTO
import kotlinx.coroutines.launch
import retrofit2.HttpException


class SignUpViewModel : ViewModel() {
    var studentService: StudentWebService = ApiManager.getStudentApi()
    var teacherService: TeacherWebService = ApiManager.getTeacherApi()
    var parentService: ParentWebService = ApiManager.getParentApi()
    var liveData = MutableLiveData<UserResponse>()
    var errorMessage = MutableLiveData<String>()
    fun addUser(userDTO: UserResponse) {
        viewModelScope.launch {
            try {
//                liveData.value = teacherService.addTeacher(
//                    TeacherResponseDTO(
//                        "test2",
//                        "test2",
//                        "test10@gmail.com",
//                        "test2",
//                        "Teacher",
//                        "+201233333335",
//                        ""
//                    )
//                )
                if(userDTO.role=="Teacher"){
                    liveData.value = teacherService.addTeacher(
                        TeacherResponseDTO(
                            userDTO.firstName,
                            userDTO.lastName,
                            userDTO.emailAddress,
                            userDTO.password,
                            userDTO.role,
                            userDTO.phone,
                            userDTO.profilePic,
                            userDTO.id
                        )
                    )
                }else if(userDTO.role=="Student"){
                    liveData.value = studentService.addStudent(
                        StudentResponseDTO(
                            userDTO.firstName,
                            userDTO.lastName,
                            userDTO.emailAddress,
                            userDTO.password,
                            userDTO.role,
                            userDTO.phone,
                            userDTO.profilePic,
                            userDTO.id
                        )
                    )
                }else if(userDTO.role=="Parent"){
                    liveData.value = parentService.addParent(
                        ParentResponseDTO(
                            userDTO.firstName,
                            userDTO.lastName,
                            userDTO.emailAddress,
                            userDTO.password,
                            userDTO.role,
                            userDTO.phone,
                            userDTO.profilePic,
                            userDTO.id
                        )
                    )
                }
            } catch (t: Throwable) {
                when (t) {
                    is HttpException ->
                        errorMessage.value=t.response()?.errorBody()?.string()
                }
            }

        }

    }
}
