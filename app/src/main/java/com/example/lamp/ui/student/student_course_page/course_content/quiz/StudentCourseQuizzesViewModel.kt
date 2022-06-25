package com.example.lamp.ui.student.student_course_page.course_content.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.api.QuizWebService
import com.example.data.model.QuizResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class StudentCourseQuizzesViewModel:ViewModel() {
    var webService: QuizWebService =ApiManager.getQuizApi()
    var liveData=MutableLiveData<List<QuizResponse>>()
    var errorMessage=MutableLiveData<String>()
    fun getAllQuizzes(courseId:Int){
        viewModelScope.launch {
            try {
                var result=webService.getQuizzesByCourseId(courseId)
                liveData.value=result
            }catch (t:Throwable){
                when(t){
                    is HttpException->{
                        errorMessage.value=t.response()?.errorBody()?.string()
                    }
                    else->{
                        errorMessage.value="error"
                    }
                }
            }
        }
    }

}