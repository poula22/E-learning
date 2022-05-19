package com.example.lamp.ui.sign_up_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lamp.R
import com.example.lamp.databinding.FragmentSignUpBinding
import java.util.*
import kotlin.concurrent.schedule

class SignUpFragment:Fragment() {
//comment test
lateinit var viewModel:SignUpViewModel
lateinit var viewBinging:FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(SignUpViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinging=DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up,container,false)
        return viewBinging.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscirbeToLiveData()
        viewModel.getData()
        viewBinging.buttonSignUpRegisteration.setOnClickListener{
            viewBinging.txtPhone.setText(viewModel.getTestData().toString())

        }
    }


    fun subscirbeToLiveData(){
                viewModel.liveData.observe(viewLifecycleOwner
        ) {

                                Log.v(
                                    "poula: ",
                                    it.toString()
                                )

//                    regions?.get(0)?.lines?.get(0)?.words?.get(0)?.text?

//            it?.regions?.get(0)?.lines?.get(0)?.words?.get(0)?.text?.let { it1 ->
//                val text=it1
//                Log.v("MSOCR",
//                    text
//                )
//            }

        }




    }
}