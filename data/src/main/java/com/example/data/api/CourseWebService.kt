package com.example.data.api

import com.example.data.model.CourseResponse
import com.example.domain.model.CourseResponseDTO
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface CourseWebService {
    @GET("api/Courses")
    suspend fun getAllCourses(): List<CourseResponse>

    @POST("api/Courses")
    suspend fun addCourse(@Body course: CourseResponseDTO): Response<Void>

    @GET("api/Courses/{id}")
    suspend fun getCourse(@Path("id") courseId: Int): CourseResponse

    @PUT("api/Courses/{id}")
    suspend fun updateCourse(@Path("id") id: Int, @Body course: CourseResponseDTO): CourseResponse

    @DELETE("api/Courses/{id}")
    suspend fun deleteCourse(@Path("id") id: Int): CourseResponse

    @GET("api/Courses/{courseId}/JoinCourse/{studentId}")
    suspend fun joinCourse(
        @Path("courseId") courseId: Int,
        @Path("studentId") studentId: Int
    ): Response<Void>

    @DELETE("api/Courses/{courseId}/DropCourse/{studentId}")
    suspend fun dropCourse(
        @Path("courseId") courseId: Int,
        @Path("studentId") studentId: Int
    ): Response<Void>

    @GET("api/Courses/GetCoursesByTeacherId/{teacherId}")
    suspend fun getCoursesByTeacherId(@Path("teacherId") teacherId: Int): List<CourseResponse>

    @GET("api/Courses/GetCoursesByStudentId/{studentId}")
    suspend fun getCoursesByStudentId(@Path("studentId") studentId: Int): List<CourseResponse>

    @Multipart
    @PUT("api/Courses/update-photo/{id}")
     fun updateCourseImageByCourseId(
        @Path("id") courseId: Int,
        @Part image: MultipartBody.Part
    ): Call<CourseResponse>

}