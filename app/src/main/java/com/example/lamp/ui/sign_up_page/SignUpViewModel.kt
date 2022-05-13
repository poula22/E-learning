package com.example.lamp.ui.sign_up_page

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
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    //MVVM
    val liveData=MutableLiveData<OCRResponseDTO?>()
    var webService: MicrosoftOCRWebService = ApiManager.getOCRApi()
    var ocrOnlineDataSource: OCROnlineDataSource = OCROnlineDataSourceImp(webService)
    var ocrRepository: OCRRepository = OCRRepositoryImp(ocrOnlineDataSource)

    fun getData(){
        viewModelScope.launch {
            val result=ocrRepository.getTextFromImage("unk"
                ,"https://yatunisia.com/wp-content/uploads/2021/02/f834ffb723ff729f4d0610b176afef10.png")

            liveData.value=result
        }
    }
}