package com.example.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object{
        var logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BASIC);
        var client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        private val BASEURL:String="https://"
        private var retrofit: Retrofit?=null
        private fun getInstance(): Retrofit {
            if (retrofit==null)
                retrofit= Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client).build()
            return retrofit!!
        }
        fun getCourseApi():CoursesWebService{
            return getInstance().create(CoursesWebService::class.java)
        }
        fun getFeatureApi():FeatureWebService{
            return getInstance().create(FeatureWebService::class.java)
        }
        fun getChildrenApi():ChildrenWebService{
            return getInstance().create(ChildrenWebService::class.java)
        }
        fun getLoginApi():LoginWebService{
            return getInstance().create(LoginWebService::class.java)
        }
    }
}