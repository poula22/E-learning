package com.example.lamp.ui.student.student_features_page.summarization

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.SummarizationOnlineDataSourceImpl
import com.example.domain.model.SummarizationResponseDTO
import com.example.domain.model.SummarizationTextRequestDTO
import com.example.lamp.ui.student.api_view_model.ApisViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SummarizationViewModel : ApisViewModel() {

    val service = ApiManager.getSummarizationApi()
    val dataSource = SummarizationOnlineDataSourceImpl(service)
    val SummarizationLiveData = MutableLiveData<SummarizationResponseDTO>()

    fun getSummarizationFromText(type : String,summarization: SummarizationTextRequestDTO) {
        viewModelScope.launch {
            try {
                SummarizationLiveData.value = dataSource.getSummarizationForText(type,summarization)
                summarization.text?.let { Log.v("summarization", it) }
                summarization.sentnum?.let { Log.v("summarization", it) }
                summarization.url?.let { Log.v("summarization", it) }
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        println(t.message)
                    }
                    else -> {
                        println(t.message)
                    }
                }
            }
        }
    }

}