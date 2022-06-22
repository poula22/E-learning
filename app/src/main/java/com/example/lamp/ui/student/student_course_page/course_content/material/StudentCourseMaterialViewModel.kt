package com.example.lamp.ui.student.student_course_page.course_content.material

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.model.ContentResponse
import com.example.data.model.LessonResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class StudentCourseMaterialViewModel:ViewModel() {
    var errorMessage=MutableLiveData<String>()
    val lessonWebService=ApiManager.getLessonApi()
    val contentWebService=ApiManager.getContentApi()

    var LessonsLiveData=MutableLiveData<List<LessonResponse>>()
    var contentLiveData=MutableLiveData<List<ContentResponse>>()

    fun getCourseLessons(courseId:Int){
        viewModelScope.launch {
            try {
                LessonsLiveData.value=lessonWebService.getLessonsByCourseId(courseId)
            }catch (t:Throwable){
                when(t){
                    is HttpException ->{
                        errorMessage.value=t.response()?.errorBody()?.string()
                    }
                }
            }

        }
    }

    fun getLessonContent(lessonId:Int){
        viewModelScope.launch {
            try {
                contentLiveData.value=contentWebService.getContentsByLessonId(lessonId)
            }catch (t:Throwable){
                when(t){
                    is HttpException ->{
                        errorMessage.value=t.response()?.errorBody()?.string()
                    }
                }
            }
        }
    }


}