package com.example.lamp.ui.sign_up_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.api.ParentWebService
import com.example.data.api.StudentWebService
import com.example.data.api.TeacherWebService
import com.example.data.model.UserResponse
import com.example.data.repos.data_sources_impl.TeacherOnlineDataSourceImpl
import com.example.domain.model.ParentResponseDTO
import com.example.domain.model.StudentResponseDTO
import com.example.domain.model.TeacherResponseDTO
import com.example.domain.repos.data_sources.TeacherOnlineDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response


class SignUpViewModel : ViewModel() {
    var studentService: StudentWebService = ApiManager.getStudentApi()
    var teacherService: TeacherWebService = ApiManager.getTeacherApi()
    var parentService: ParentWebService = ApiManager.getParentApi()
    var liveData = MutableLiveData<UserResponse>()
    var test = MutableLiveData<Response<Void>>()
    var errorMessage = MutableLiveData<String>()

    val teacherDataSource:TeacherOnlineDataSource=TeacherOnlineDataSourceImpl(teacherService)

    val auth: FirebaseAuth = Firebase.auth
    var verifiedMessage: String = ""
    var exception: Exception? = null
    var resultFirebase=MutableLiveData<Boolean>()

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
                if (userDTO.role == "Teacher") {
                    test.value = teacherDataSource.addTeacher(
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
                } else if (userDTO.role == "Student") {
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
                } else if (userDTO.role == "Parent") {
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
                        errorMessage.value = t.response()?.errorBody()?.string()
                }
            }

        }

    }

    fun addUserToFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task1 ->
                var result = task1.isSuccessful
                resultFirebase.value=result
                if (result) {
                    val user = FirebaseAuth.getInstance().currentUser
                    user?.sendEmailVerification()
                    verifiedMessage = "Verification email sent"

                } else {
                    exception = task1.exception
                    verifiedMessage = exception?.message.toString()
                }
            }
    }
}
