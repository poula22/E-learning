package com.example.lamp.ui.teacher.courses_page.course_content.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.model.QuestionAnswerResponse
import com.example.data.model.QuestionChoiceResponse
import com.example.domain.model.QuestionResponseDTO
import kotlinx.coroutines.launch
import retrofit2.HttpException

class FragmentTeacherCourseQuizAddQuestionsViewModel:ViewModel() {

    val errorMessage= MutableLiveData<String>()
    val liveData= MutableLiveData<List<QuestionChoiceResponse>>()
    val questionService= ApiManager.getQuestionApi()
    val answerService= ApiManager.getQuestionChoiceApi()
    fun addQuiz(questionResponse: QuestionResponseDTO){
        viewModelScope.launch {
            try {
                questionService.addQuestion(questionResponse)
            }catch (t:Throwable){
                when(t){
                    is HttpException ->{
                        errorMessage.value=t.response()?.errorBody()?.string()
                    }
                    else ->{
                        println("unknown error")
                    }
                }
            }
        }
    }

    fun getQuestionAnswers(questionId:Int){
        viewModelScope.launch {
            try {
                liveData.value=answerService.getQuestionChoicesByQuestionId(questionId)
            }catch (t:Throwable){
                when(t){
                    is HttpException ->{
                        errorMessage.value=t.response()?.errorBody()?.string()
                    }
                    else ->{
                        println("unknown error")
                    }
                }
            }
        }
    }

}