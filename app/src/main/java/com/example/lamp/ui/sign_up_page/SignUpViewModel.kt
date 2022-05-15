package com.example.lamp.ui.sign_up_page

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.api.microsoft_api.ocr.MicrosoftOCRWebService
import com.example.data.repos.OCROnlineDataSourceImp
import com.example.data.repos.OCRRepositoryImp
import com.example.domain.model.ReadOCRResponseDTO
import com.example.domain.repos.OCROnlineDataSource
import com.example.domain.repos.OCRRepository
import com.microsoft.cognitiveservices.speech.*
import com.microsoft.cognitiveservices.speech.audio.AudioConfig
import com.microsoft.cognitiveservices.speech.samples.sdkdemo.MicrophoneStream
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


class SignUpViewModel : ViewModel() {
    //MVVM
//    val liveData=MutableLiveData<OCRResponseDTO?>()
    val liveData=MutableLiveData<ReadOCRResponseDTO?>()
    var ocrOnlineDataSource: OCROnlineDataSource = OCROnlineDataSourceImp()
    var ocrRepository: OCRRepository = OCRRepositoryImp(ocrOnlineDataSource)

    var microphoneStream:MicrophoneStream?=null

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
    fun test(txt:TextView){

    }

    }
