package com.example.lamp.ui.student.student_features_page.summarization

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.SummarizationOnlineDataSourceImpl
import com.example.domain.model.SummarizationResponseDTO
import com.example.domain.model.SummarizationTextRequestDTO
import com.example.domain.model.SummarizationUrlRequestDTO
import com.example.lamp.ui.student.api_view_model.ApisViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SummarizationViewModel : ApisViewModel() {

    val service = ApiManager.getSummarizationApi()
    val dataSource = SummarizationOnlineDataSourceImpl(service)
    val SummarizationLiveData = MutableLiveData<SummarizationResponseDTO>()

    fun getSummarizationFromText(summarization: SummarizationTextRequestDTO) {
        viewModelScope.launch {
            try {
                SummarizationLiveData.value = dataSource.getSummarizationForText(summarization)
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        println("HttpException")
                    }
                    else -> {
                        println(t.message)
                    }
                }
            }
        }
    }

    fun getSummarizationFromUrl(summarization: SummarizationUrlRequestDTO) {
        viewModelScope.launch {
            try {
                val response = dataSource.getSummarizationForUrl(summarization)
                SummarizationLiveData.value = response
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        println("HttpException")
                    }
                    else -> {
                        println("Throwable")
                    }
                }
            }

        }
    }


}