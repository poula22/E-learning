package com.example.lamp.ui.teacher.courses_page.course_content.assignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class PDFViewerViewModel:ViewModel() {
    val testLiveData= MutableLiveData<ResponseBody>()
    val testService= ApiManager.getFileTransferApi()
    fun getFile(fileName:String){
        viewModelScope.launch {
            try {
                testLiveData.value= testService.getFile(fileName)
            }catch (t:Throwable){
                t.printStackTrace()
            }
        }
    }
}