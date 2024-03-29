package com.example.lamp.ui.teacher.profile_page

import TeacherProfileViewModel
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CONSTANTS
import com.example.common_functions.ExternalStorageAccessFragment
import com.example.domain.model.UserResponseDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherProfileBinding
import com.example.lamp.ui.sign_in_page.SigninFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File

class TeacherProfileFragment : ExternalStorageAccessFragment() {
    lateinit var viewBinding: FragmentTeacherProfileBinding
    lateinit var viewModel: TeacherProfileViewModel
    var email: String? = null
    var role: String? = null
    var profilePictuerURL: String? = null
    override fun showProgressBar() {

    }

    override fun resultListener(byteArray: ByteArray) {
        val file = filePath?.let {
            File(it)
        }
        filePath?.let {
            Log.v("filePath of image", it)
        }
        if (file != null) {
            viewModel.changeUserImage(file)
        }
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeacherProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_teacher_profile, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
        initViews()
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModel.getUserInfo()
    }

    private fun initViews() {
        viewBinding.changeImage.setOnClickListener {
            imagePick()
        }

        viewBinding.editProfileSubmit.setOnClickListener {
            val user = UserResponseDTO(
                viewBinding.firstName.editText?.text.toString(),
                viewBinding.lastName.editText?.text.toString(),
                email,
                viewBinding.password.editText?.text.toString(),
                role,
                viewBinding.phone.editText?.text.toString(),
                profilePictuerURL,
                CONSTANTS.user_id
            )
            updateUserInfo(user)

        }
        viewBinding.logout.setOnClickListener {
            viewModel.logOut()
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.teacher_fragment_tab,
                SigninFragment()
            ).commit()
            requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation_view).visibility =
                View.GONE

        }
    }

    private fun updateUserInfo(user: UserResponseDTO) {
        viewModel.updateUserInfo(CONSTANTS.user_id, user)
    }

    private fun subscribeToLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            viewBinding.firstName.editText?.setText(it.firstName)
            viewBinding.lastName.editText?.setText(it.lastName)
            viewBinding.phone.editText?.setText(it.phone)
            email = it.emailAddress
            role = it.role
            profilePictuerURL = it.profilePic
            viewModel.getImage(it.profilePic.toString())
//            viewBinding.roundedProfile.setBackgroundResource(it.profilePic)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Log.v("ProfileFragment::", it)
        }
        viewModel.testLiveData.observe(viewLifecycleOwner) {
            viewBinding.roundedImageView.setImageBitmap(BitmapFactory.decodeStream(it.byteStream()))
        }
        viewModel.userUpdateLiveData.observe(viewLifecycleOwner) {
            viewModel.getUserInfo()
        }
    }


}