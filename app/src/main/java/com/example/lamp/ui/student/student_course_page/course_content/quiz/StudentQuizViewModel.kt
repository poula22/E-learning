package com.example.lamp.ui.student.student_course_page.course_content.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.model.QuestionResponse
import com.example.data.model.QuizDetailsResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class StudentQuizViewModel:ViewModel() {
    val liveData=MutableLiveData<List<QuizDetailsResponse>>()
    val errorMessage=MutableLiveData<String>()
    private val webService=ApiManager.getQuestionApi()
    fun getQuizQuestions(quizId:Int){
        viewModelScope.launch {
            try {

                liveData.value=webService.getQuestionsByQuizId(quizId)
            }catch (t:Throwable){
                when(t){
                    is HttpException ->{
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