package com.example.lamp.ui.sign_up_page

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.ExternalStorageAccessFragment
import com.example.data.model.UserResponse
import com.example.lamp.R
import com.example.lamp.databinding.FragmentSignUpBinding
import com.example.lamp.ui.sign_in_page.SigninFragment
import java.util.regex.Pattern

class SignUpFragment : ExternalStorageAccessFragment() {
    //comment test
    lateinit var viewModel: SignUpViewModel
    lateinit var viewBinding: FragmentSignUpBinding
    var selected: String? = null
    var verified = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return viewBinding.root
    }

    private fun subscirbeToLiveData() {
        viewModel.liveData.observe(
            viewLifecycleOwner
        ) {
            Log.v("response test::", it.toString())
        }

        viewModel.errorMessage.observe(
            viewLifecycleOwner
        ) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            verified = false
        }
        viewModel.resultFirebase.observe(
            viewLifecycleOwner
        ) {
            if (it) {
                Toast.makeText(requireContext(), "Verification email sent", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Try again", Toast.LENGTH_SHORT)
                    .show()
            }
            if (it && verified) {
                Toast.makeText(requireContext(), "Successfully registered", Toast.LENGTH_SHORT)
                    .show()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SigninFragment())
                    .commit()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscirbeToLiveData()
        initViews()
    }

    private fun initViews() {
        viewBinding.childrenLayout.isVisible = false
        viewBinding.imageStudent.setOnClickListener {
            it.setBackgroundResource(R.drawable.rounded_frame_yellow)
            viewBinding.imageParent.setBackgroundResource(R.drawable.rounded_frame)
            viewBinding.imageTeacher.setBackgroundResource(R.drawable.rounded_frame)
            selected = "Student"
            viewBinding.childrenLayout.isVisible = false
        }
        viewBinding.imageTeacher.setOnClickListener {
            it.setBackgroundResource(R.drawable.rounded_frame_yellow)
            viewBinding.imageParent.setBackgroundResource(R.drawable.rounded_frame)
            viewBinding.imageStudent.setBackgroundResource(R.drawable.rounded_frame)
            selected = "Teacher"
            viewBinding.childrenLayout.isVisible = false
        }
        viewBinding.imageParent.setOnClickListener {
            it.setBackgroundResource(R.drawable.rounded_frame_yellow)
            viewBinding.imageTeacher.setBackgroundResource(R.drawable.rounded_frame)
            viewBinding.imageStudent.setBackgroundResource(R.drawable.rounded_frame)
            selected = "Parent"
            viewBinding.childrenLayout.isVisible = true
        }

        viewBinding.card.setOnClickListener {
            imagePick()
        }

        viewBinding.buttonSignUpRegisteration.setOnClickListener {

            if (validate()) {
                var firstName = viewBinding.firstName.editText?.text.toString()
                var lastName = viewBinding.lastName.editText?.text.toString()
                var email = viewBinding.email.text.toString()
                var password = viewBinding.passwordSignUp.editText?.text.toString()
                var phoneNumber = viewBinding.txtPhone.text.toString()

                viewModel.addUser(
                    UserResponse(
                        firstName,
                        lastName,
                        email,
                        password,
                        selected,
                        phoneNumber
                    )
                )
                viewModel.addUserToFirebase(email, password)

            }
        }
    }


    fun validate(): Boolean {

        var isValid = true
        if (viewBinding.emailSignUp.editText?.text.toString().isEmpty()) {
            viewBinding.emailSignUp.error = "Email is required"
            isValid = false
        } else if (!Pattern.matches(
                "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
                viewBinding.email.text.toString()
            )
        ) {
            viewBinding.emailSignUp.error = "Incorrect email address"
            isValid = false
        } else {
            viewBinding.emailSignUp.error = null
        }
        if (viewBinding.txtPhone.text.toString().isEmpty()) {
            viewBinding.txtPhone.error = "Phone is required"
            isValid = false
        } else {
            viewBinding.txtPhone.error = null
        }
        if (!Pattern.matches("^1[0125][0-9]{8}$", viewBinding.txtPhone.text.toString())) {
            viewBinding.txtPhone.error = "Phone number is incorrect"
            isValid = false
        } else {
            viewBinding.txtPhone.error = null
        }
        if (viewBinding.passwordSignUp.editText?.text.toString().isEmpty()) {
            viewBinding.passwordSignUp.error = "Password is required"
            isValid = false
        } else if (viewBinding.passwordSignUp.editText?.text.toString().length < 6) {
            viewBinding.passwordSignUp.error = "Password must be at least 6 characters"
            isValid = false
        } else {
            viewBinding.passwordSignUp.error = null
        }
        if (viewBinding.passwordSignUp.editText?.text.toString() != viewBinding.confirmPasswordSignUp.editText?.text.toString()) {
            viewBinding.confirmPasswordSignUp.error = "Password does not match"
            isValid = false
        } else {
            viewBinding.confirmPasswordSignUp.error = null
        }
        if (viewBinding.firstName.editText?.text.toString().isEmpty()) {

            viewBinding.firstName.error = "First Name is required"
            isValid = false

        }else if (!Pattern.matches("[a-zA-Z]+", viewBinding.firstName.editText?.text.toString())) {

            viewBinding.firstName.error = "First Name should be letters only"
            isValid = false

        } else {

            viewBinding.firstName.error = null

        }
        if (viewBinding.lastName.editText?.text.toString().isEmpty()) {

            viewBinding.lastName.error = "Last Name is required"
            isValid = false

        }else if (!Pattern.matches("[a-zA-Z]+", viewBinding.lastName.editText?.text.toString())) {

            viewBinding.lastName.error = "Last Name should be letters only"
            isValid = false

        } else {

            viewBinding.lastName.error = null

        }
        if (selected == null) {
            Toast.makeText(context, "Please select your role", Toast.LENGTH_SHORT).show()
            isValid = false
        } else if (selected == "Parent") {
            if (viewBinding.childMail.editText?.text.toString().isEmpty()
                && viewBinding.childEmail2.editText?.text.toString().isEmpty()
                && viewBinding.childEmail3.editText?.text.toString().isEmpty()
                && viewBinding.childEmail4.editText?.text.toString().isEmpty()
            ) {
                viewBinding.childMail.error = "At least one child email is required"
            }
        }
        return isValid
    }


    override fun showProgressBar() {

    }

    override fun resultListener(byteArray: ByteArray) {
        var bmp: Bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        viewBinding.chooseImageTxt.isVisible = false
//        viewBinding.profileImageView.setImageBitmap(bmp)
        Log.v("image", "done")
        val image = viewBinding.profileImageView
        image.setImageBitmap(Bitmap.createScaledBitmap(bmp, image.width, image.height, false))
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

}
