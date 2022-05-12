package com.example.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object {
        var logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BASIC
        );
        var client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        private val BASEURL: String = "https://"
        private var retrofit: Retrofit? = null
        private fun getInstance(): Retrofit {
            if (retrofit == null)
                retrofit = Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client).build()
            return retrofit!!
        }

        fun getCourseApi(): CourseWebService {
            return getInstance().create(CourseWebService::class.java)
        }

        fun getFeatureApi(): FeatureWebService {
            return getInstance().create(FeatureWebService::class.java)
        }

        fun getLoginInfoApi(): LoginInfoWebService {
            return getInstance().create(LoginInfoWebService::class.java)
        }

        fun getAnnouncementApi(): AnnouncementWebService {
            return getInstance().create(AnnouncementWebService::class.java)
        }

        fun getAssignmentAnswerApi(): AssignmentAnswerWebService {
            return getInstance().create(AssignmentAnswerWebService::class.java)
        }

        fun getAssignmentApi(): AssignmentWebService {
            return getInstance().create(AssignmentWebService::class.java)
        }

        fun getQuestionApi(): QuestionWebService {
            return getInstance().create(QuestionWebService::class.java)
        }

        fun getQuizApi(): QuizWebService {
            return getInstance().create(QuizWebService::class.java)
        }

        fun getQuizAnswerApi(): QuizAnswerWebService {
            return getInstance().create(QuizAnswerWebService::class.java)
        }

        fun getQuizGradeApi(): QuizGradeWebService {
            return getInstance().create(QuizGradeWebService::class.java)
        }

        fun getAssignmentFeedbackApi(): AssignmentFeedbackWebService {
            return getInstance().create(AssignmentFeedbackWebService::class.java)
        }

        fun getAssignmentGradeApi(): AssignmentGradeWebService {
            return getInstance().create(AssignmentGradeWebService::class.java)
        }

        // Badge
        fun getBadgeApi(): BadgeWebService {
            return getInstance().create(BadgeWebService::class.java)
        }

        // Content
        fun getContentApi(): ContentWebService {
            return getInstance().create(ContentWebService::class.java)
        }

        // LatestPassedLesson
        fun getLatestPassedLessonApi(): LatestPassedLessonWebService {
            return getInstance().create(LatestPassedLessonWebService::class.java)
        }

        // Lesson
        fun getLessonApi(): LessonWebService {
            return getInstance().create(LessonWebService::class.java)
        }

        // Note
        fun getNoteApi(): NoteWebService {
            return getInstance().create(NoteWebService::class.java)
        }

        // Parent
        fun getParentApi(): ParentWebService {
            return getInstance().create(ParentWebService::class.java)
        }

        //Question
        fun getQuestionAnswerApi(): QuestionAnswerWebService {
            return getInstance().create(QuestionAnswerWebService::class.java)
        }

        // Resource
        fun getResourceApi(): ResourceWebService {
            return getInstance().create(ResourceWebService::class.java)
        }

        //Student
        fun getStudentApi(): StudentWebService {
            return getInstance().create(StudentWebService::class.java)
        }

        // Teacher
        fun getTeacherApi(): TeacherWebService {
            return getInstance().create(TeacherWebService::class.java)
        }

        //TodoList
        fun getToDoListApi(): ToDoListWebService {
            return getInstance().create(ToDoListWebService::class.java)
        }


    }
}