package com.example.lamp.ui.sign_up_page

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.api.microsoft_api.ocr.MicrosoftOCRWebService
import com.example.data.repos.OCROnlineDataSourceImp
import com.example.data.repos.OCRRepositoryImp
import com.example.domain.repos.OCROnlineDataSource
import com.example.domain.repos.OCRRepository
import com.example.domain.model.OCRResponseDTO
import com.example.domain.model.ReadOCRResponseDTO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class SignUpViewModel : ViewModel() {
    //MVVM
//    val liveData=MutableLiveData<OCRResponseDTO?>()
    val liveData=MutableLiveData<ReadOCRResponseDTO?>()
    var webService: MicrosoftOCRWebService = ApiManager.getOCRApi()
    var ocrOnlineDataSource: OCROnlineDataSource = OCROnlineDataSourceImp(webService)
    var ocrRepository: OCRRepository = OCRRepositoryImp(ocrOnlineDataSource)

    fun getData(){
        viewModelScope.launch {
//            val result=ocrRepository.getTextFromImage("unk"
//                ,"https://ocr-demo.abtosoftware.com/uploads/handwritten2.jpg")
            try{
                var result=ocrRepository.getTextFromImageReadApi(url = "https://ocr-demo.abtosoftware.com/uploads/handwritten2.jpg")
                liveData.value=result
            }catch (e:Exception){

            }



        }

    }
}