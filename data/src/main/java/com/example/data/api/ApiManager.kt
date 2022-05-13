package com.example.data.api

import com.example.data.api.microsoft_api.ocr.MicrosoftOCRWebService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object {
        var logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BASIC
        )
        var client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        private const val BASEURL_BACKEND: String = "https://"
        private const val BASEURL_OCR:String="https://eastus.api.cognitive.microsoft.com/"
        private var retrofitBackend: Retrofit? = null
        private var retrofitOCR:Retrofit?=null

        private fun getBackendInstance(): Retrofit {
            if (retrofitBackend == null)
                retrofitBackend = Retrofit.Builder()
                    .baseUrl(BASEURL_BACKEND)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client).build()
            return retrofitBackend!!
        }

        private fun getOCRInstance(): Retrofit {
            if (retrofitOCR == null)
                retrofitOCR = Retrofit.Builder()
                    .baseUrl(BASEURL_OCR)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client).build()
            return retrofitOCR!!
        }

        fun getCourseApi(): CourseWebService {
            return getBackendInstance().create(CourseWebService::class.java)
        }

        fun getFeatureApi(): FeatureWebService {
            return getBackendInstance().create(FeatureWebService::class.java)
        }

        fun getLoginInfoApi(): LoginInfoWebService {
            return getBackendInstance().create(LoginInfoWebService::class.java)
        }

        fun getAnnouncementApi(): AnnouncementWebService {
            return getBackendInstance().create(AnnouncementWebService::class.java)
        }

        fun getAssignmentAnswerApi(): AssignmentAnswerWebService {
            return getBackendInstance().create(AssignmentAnswerWebService::class.java)
        }

        fun getAssignmentApi(): AssignmentWebService {
            return getBackendInstance().create(AssignmentWebService::class.java)
        }

        fun getQuestionApi(): QuestionWebService {
            return getBackendInstance().create(QuestionWebService::class.java)
        }

        fun getQuizApi(): QuizWebService {
            return getBackendInstance().create(QuizWebService::class.java)
        }

        fun getQuizAnswerApi(): QuizAnswerWebService {
            return getBackendInstance().create(QuizAnswerWebService::class.java)
        }

        fun getQuizGradeApi(): QuizGradeWebService {
            return getBackendInstance().create(QuizGradeWebService::class.java)
        }

        fun getAssignmentFeedbackApi(): AssignmentFeedbackWebService {
            return getBackendInstance().create(AssignmentFeedbackWebService::class.java)
        }

        fun getAssignmentGradeApi(): AssignmentGradeWebService {
            return getBackendInstance().create(AssignmentGradeWebService::class.java)
        }

        // Badge
        fun getBadgeApi(): BadgeWebService {
            return getBackendInstance().create(BadgeWebService::class.java)
        }

        // Content
        fun getContentApi(): ContentWebService {
            return getBackendInstance().create(ContentWebService::class.java)
        }

        // LatestPassedLesson
        fun getLatestPassedLessonApi(): LatestPassedLessonWebService {
            return getBackendInstance().create(LatestPassedLessonWebService::class.java)
        }

        // Lesson
        fun getLessonApi(): LessonWebService {
            return getBackendInstance().create(LessonWebService::class.java)
        }

        // Note
        fun getNoteApi(): NoteWebService {
            return getBackendInstance().create(NoteWebService::class.java)
        }

        // Parent
        fun getParentApi(): ParentWebService {
            return getBackendInstance().create(ParentWebService::class.java)
        }

        //Question
        fun getQuestionAnswerApi(): QuestionAnswerWebService {
            return getBackendInstance().create(QuestionAnswerWebService::class.java)
        }

        // Resource
        fun getResourceApi(): ResourceWebService {
            return getBackendInstance().create(ResourceWebService::class.java)
        }

        //Student
        fun getStudentApi(): StudentWebService {
            return getBackendInstance().create(StudentWebService::class.java)
        }

        // Teacher
        fun getTeacherApi(): TeacherWebService {
            return getBackendInstance().create(TeacherWebService::class.java)
        }

        //TodoList
        fun getToDoListApi(): ToDoListWebService {
            return getBackendInstance().create(ToDoListWebService::class.java)
        }

        //OCR
        fun getOCRApi():MicrosoftOCRWebService{
            return getOCRInstance().create(MicrosoftOCRWebService::class.java)
        }

    }
}