package com.example.lamp.ui.student.api_view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.OCRRepositoryImp
import com.example.data.repos.data_sources_impl.OCROnlineDataSourceImp
import com.example.domain.repos.OCRRepository
import com.example.domain.repos.data_sources.OCROnlineDataSource
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadOperationResult
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//ocr view model
open class ApisViewModel : ViewModel() {
    val liveData = MutableLiveData<ReadOperationResult>()
    var webService = ApiManager.getOCRApi()
    var ocrOnlineDataSource: OCROnlineDataSource = OCROnlineDataSourceImp(webService)
    var ocrRepository: OCRRepository = OCRRepositoryImp(ocrOnlineDataSource)

    fun getData(image: ByteArray?) {
        Thread {
            try {
                runBlocking {
                    if (image != null) {
                        var result = ocrRepository.getTextFromImageReadApi(image = image)
                        viewModelScope.launch {
                            liveData.value = result
                        }
                    }

                }
            } catch (e: Exception) {

            }
        }.start()
    }
}