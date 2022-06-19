package com.example.lamp.ui.sign_up_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.data.model.convertTo
import com.example.domain.model.TeacherResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentSignUpBinding
import java.util.*
import kotlin.concurrent.schedule

class SignUpFragment:Fragment() {
//comment test
lateinit var viewModel:SignUpViewModel
lateinit var viewBinging:FragmentSignUpBinding
var selected:String?=null

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
    private fun subscirbeToLiveData() {
        viewModel.liveData.observe(
            viewLifecycleOwner
        ) {
            Log.v("response test::",it.firstName!!)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscirbeToLiveData()
        initViews()
    }

    private fun initViews() {
      viewBinging.imageStudent.setOnClickListener {
          it.setBackgroundColor(resources.getColor(R.color.green))
          selected="Student"
    }
        viewBinging.imageTeacher.setOnClickListener {
            it.setBackgroundColor(resources.getColor(R.color.green))
            selected="Teacher"
        }
        viewBinging.imageParent.setOnClickListener {
            it.setBackgroundColor(resources.getColor(R.color.green))
            selected="Parent"
        }
        viewBinging.buttonSignUpRegisteration.setOnClickListener{
            if(selected =="Teacher"){
                viewModel.addUser()
            }

        }

    }

}
