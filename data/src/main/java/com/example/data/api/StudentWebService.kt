package com.example.data.api

import com.example.data.data_classes.Student
import com.example.data.model.StudentResponse
import com.example.data.model.UserResponse
import com.example.domain.model.StudentResponseDTO
import retrofit2.Call
import retrofit2.http.*

interface StudentWebService {
    @POST("api/Students")
    suspend fun addStudent(@Path("student") student: StudentResponseDTO):UserResponse
    @GET("api/Students/GetStudentsByParentId/{parentId}")
    suspend fun updateStudent(@Query("parentId") parentId:Int):StudentResponse
    @GET("api/Students/GetStudentsByCourseId/{courseId}")
    suspend fun getStudentByCourseId(@Path("courseId") courseId:Int):List<StudentResponse>
    @GET("Email/{email}")
    suspend fun getStudentByEmail(@Path("email") email:String):StudentResponse
}