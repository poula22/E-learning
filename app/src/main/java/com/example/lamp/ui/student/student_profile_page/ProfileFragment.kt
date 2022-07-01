package com.example.lamp.ui.student.student_profile_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.domain.model.UserResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var viewBinding: FragmentStudentProfileBinding
    lateinit var viewModel: ProfileViewModel
    var email:String?=null
    var role:String?=null
    var profilePictuerURL:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_student_profile, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initViews()
        viewModel.getUserInfo(CONSTANTS.user_id)
    }

    private fun subscribeToLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner){
            viewBinding.firstName.editText?.setText(it.firstName)
            viewBinding.lastName.editText?.setText(it.lastName)
            viewBinding.phone.editText?.setText(it.phone)
            email=it.emailAddress
            role=it.role
            profilePictuerURL=it.profilePic
//            viewBinding.roundedProfile.setBackgroundResource(it.profilePic)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner){
            Log.v("ProfileFragment::",it)
        }
    }

    private fun initViews() {
        viewBinding.changeImage.setOnClickListener {

        }

        viewBinding.editProfileSubmit.setOnClickListener {
            val user=UserResponseDTO(
                viewBinding.firstName.editText?.text.toString(),
                viewBinding.lastName.editText?.text.toString(),
                email,
                viewBinding.password.editText?.text.toString(),
                role,
                viewBinding.phone.editText?.text.toString(),
                profilePictuerURL,
                CONSTANTS.user_id
            )
            viewModel.updateUserInfo(CONSTANTS.user_id,user)
        }
    }


}