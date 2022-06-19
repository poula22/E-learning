package com.example.lamp.ui.sign_up_page

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.OCROnlineDataSourceImp
import com.example.data.repos.OCRRepositoryImp
import com.example.domain.repos.OCROnlineDataSource
import com.example.domain.repos.OCRRepository
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadOperationResult
import com.microsoft.cognitiveservices.speech.samples.sdkdemo.MicrophoneStream
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class SignUpViewModel : ViewModel() {
    //MVVM
//    val liveData=MutableLiveData<OCRResponseDTO?>()
    val liveData=MutableLiveData<ReadOperationResult>()
    var ocrOnlineDataSource: OCROnlineDataSource = OCROnlineDataSourceImp()
    var ocrRepository: OCRRepository = OCRRepositoryImp(ocrOnlineDataSource)



    var microphoneStream:MicrophoneStream?=null

    fun getData(){
        Thread{

//            val result=ocrRepository.getTextFromImage("unk"
//                ,"https://ocr-demo.abtosoftware.com/uploads/handwritten2.jpg")
            try{
                    runBlocking {
                        var result= ocrRepository.getTextFromImageReadApi(url = "https://ocr-demo.abtosoftware.com/uploads/handwritten2.jpg")
                        viewModelScope.launch { liveData.value=result }

                    }



            }catch (e:Exception){

            }

        }.start()
    }

    fun getTestData(): ReadOperationResult {
        return liveData.value!!
    }

    fun test(txtPhone: EditText) {

    }


}
