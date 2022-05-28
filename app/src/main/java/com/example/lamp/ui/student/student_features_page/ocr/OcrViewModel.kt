package com.example.lamp.ui.student.student_features_page.ocr

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repos.OCROnlineDataSourceImp
import com.example.data.repos.OCRRepositoryImp
import com.example.domain.repos.OCROnlineDataSource
import com.example.domain.repos.OCRRepository
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadOperationResult
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class OcrViewModel : ViewModel() {
    val liveData = MutableLiveData<ReadOperationResult>()
    var ocrOnlineDataSource: OCROnlineDataSource = OCROnlineDataSourceImp()
    var ocrRepository: OCRRepository = OCRRepositoryImp(ocrOnlineDataSource)

     fun getData(image: ByteArray?) {
        Thread {
            try {
                runBlocking {
                    if (image!=null){
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