package com.example.lamp.ui.student.student_features_page.recitation.recite_paragraph

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.repos.data_sources_impl.RecitationParagraphOnlineDataSourceImpl
import com.example.domain.model.RecitationParagraphRequestDTO
import com.example.domain.model.RecitationParagraphResponseDTO
import com.example.lamp.ui.student.api_view_model.ApisViewModel
import kotlinx.coroutines.launch

class ReciteParagraphViewModel : ApisViewModel() {

    var service = ApiManager.getRecitationApi()
    var dataSource = RecitationParagraphOnlineDataSourceImpl(service)
    var recitationLiveData = MutableLiveData<RecitationParagraphResponseDTO>()

    fun getSimilarity(request: RecitationParagraphRequestDTO) {
        viewModelScope.launch {
            try {
                recitationLiveData.value = dataSource.getSimilarity(request)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }


}