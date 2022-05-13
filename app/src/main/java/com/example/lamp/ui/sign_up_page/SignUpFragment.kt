package com.example.lamp.ui.sign_up_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.viewModelScope
import com.example.data.api.ApiManager
import com.example.data.api.microsoft_api.ocr.MicrosoftOCRWebService
import com.example.data.repos.OCROnlineDataSourceImp
import com.example.data.repos.OCRRepositoryImp
import com.example.domain.repos.OCROnlineDataSource
import com.example.domain.repos.OCRRepository
import com.example.domain.repos.OCRResponseDTO
import com.example.lamp.R
import java.util.logging.Logger

class SignUpFragment:Fragment() {
//comment test
lateinit var viewModel:SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(SignUpViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscirbeToLiveData()
        viewModel.getData()
    }

    fun subscirbeToLiveData(){
                viewModel.liveData.observe(viewLifecycleOwner
        ) {
//                    regions?.get(0)?.lines?.get(0)?.words?.get(0)?.text?
            it?.regions?.get(0)?.lines?.get(0)?.words?.get(0)?.text?.let { it1 ->
                val text=it1
                Log.v("MSOCR",
                    text
                )
            }

        }
    }
}