package com.example.lamp.ui.sign_up_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lamp.R
import java.util.*
import kotlin.concurrent.schedule

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
                    it.let { it1 ->
                        Log.v("MSOCR",
                            it1.toString()
                        )
                    }

//            it?.regions?.get(0)?.lines?.get(0)?.words?.get(0)?.text?.let { it1 ->
//                val text=it1
//                Log.v("MSOCR",
//                    text
//                )
//            }

        }



    }
}