package com.example.data.api

import com.example.data.api.microsoft_api.ocr.MicrosoftOCRWebService
import com.microsoft.azure.cognitiveservices.vision.computervision.implementation.ComputerVisionClientImpl
import com.microsoft.azure.cognitiveservices.vision.computervision.implementation.ComputerVisionImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*

class ApiManager {
    companion object {
        var logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BASIC
        )
        var client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        var unSafeClient = getUnsafeOkHttpClient()?.addInterceptor(logging)?.build()


        private const val BASEURL_BACKEND: String = "https://25.46.88.203:7097/"
        private const val BASEURL_OCR: String = "https://eastus.api.cognitive.microsoft.com/"
        private var retrofitBackend: Retrofit? = null
        private var retrofitApi: Retrofit? = null

        fun getRetrofitVisionApi():ComputerVisionImpl{
            val vision: ComputerVisionImpl = ComputerVisionImpl(retrofitApi,client as ComputerVisionClientImpl)
            return vision
        }

        private fun getBackendInstance(): Retrofit {
            if (retrofitBackend == null)
                retrofitBackend = Retrofit.Builder()
                    .baseUrl(BASEURL_BACKEND)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(unSafeClient).build()
            return retrofitBackend!!
        }

        private fun getOCRInstance(): Retrofit {
            if (retrofitApi == null)
                retrofitApi = Retrofit.Builder()
                    .baseUrl(BASEURL_OCR)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client).build()
            return retrofitApi!!
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
        fun getOCRApi(): MicrosoftOCRWebService {
            return getOCRInstance().create(MicrosoftOCRWebService::class.java)
        }


        fun getLoginApi(): LoginInfoWebService {
            return getBackendInstance().create(LoginInfoWebService::class.java)
        }


        private fun getUnsafeOkHttpClient(): OkHttpClient.Builder? {
            return try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                            return arrayOf()
                        }
                    }
                )

                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())
                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory
                val trustManagerFactory: TrustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                trustManagerFactory.init(null as KeyStore?)
                val trustManagers: Array<TrustManager> =
                    trustManagerFactory.trustManagers
                check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
                    "Unexpected default trust managers:" + trustManagers.contentToString()
                }

                val trustManager =
                    trustManagers[0] as X509TrustManager


                val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(sslSocketFactory, trustManager)
                builder.hostnameVerifier(HostnameVerifier { _, _ -> true })
                builder
            } catch (e: Exception) {
                throw RuntimeException(e)
            }


        }
    }
}