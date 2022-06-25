package com.example.lamp.ui.student.student_course_page.course_content.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.api.QuizWebService
import com.example.data.model.QuizResponse
import kotlinx.coroutines.launch

class StudentCourseQuizzesViewModel:ViewModel() {
    var webService: QuizWebService =ApiManager.getQuizApi()
    var liveData=MutableLiveData<List<QuizResponse>>()
    fun getAllQuizzes(courseId:Int){
        viewModelScope.launch {
            liveData.value=webService.getQuizzesByCourseId(courseId)
        }
    }

}