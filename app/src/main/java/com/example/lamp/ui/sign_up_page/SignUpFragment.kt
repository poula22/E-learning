package com.example.lamp.ui.sign_up_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lamp.R
import com.example.lamp.databinding.FragmentSignUpBinding
import java.util.regex.Pattern

class SignUpFragment : Fragment() {
    //comment test
    lateinit var viewModel: SignUpViewModel
    lateinit var viewBinding: FragmentSignUpBinding
    var selected: String? = null

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
            Log.v("response test::", it.emailAddress!!)
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
            it.setBackgroundColor(R.color.green)
            viewBinding.imageParent.setBackgroundResource(R.drawable.rounded_frame)
            viewBinding.imageTeacher.setBackgroundResource(R.drawable.rounded_frame)
            selected = "Student"
            viewBinding.childrenLayout.isVisible = false
        }
        viewBinding.imageTeacher.setOnClickListener {
            it.setBackgroundColor(R.color.green)
            viewBinding.imageParent.setBackgroundResource(R.drawable.rounded_frame)
            viewBinding.imageStudent.setBackgroundResource(R.drawable.rounded_frame)
            selected = "Teacher"
            viewBinding.childrenLayout.isVisible = false
        }
        viewBinding.imageParent.setOnClickListener {
            it.setBackgroundColor(R.color.green)
            viewBinding.imageTeacher.setBackgroundResource(R.drawable.rounded_frame)
            viewBinding.imageStudent.setBackgroundResource(R.drawable.rounded_frame)
            selected = "Parent"
            viewBinding.childrenLayout.isVisible = true
        }

        viewBinding.buttonSignUpRegisteration.setOnClickListener {
            if (validate()) {
                viewModel.addUser()
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
        } else {
            viewBinding.txtPhone.error = null
        }
        if (viewBinding.passwordSignUp.editText?.text.toString().isEmpty()) {
            viewBinding.passwordSignUp.error = "Password is required"
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
        } else {
            viewBinding.firstName.error = null
        }
        if (viewBinding.lastName.editText?.text.toString().isEmpty()) {
            viewBinding.lastName.error = "Last Name is required"
            isValid = false
        } else {
            viewBinding.lastName.error = null
        }
        if (selected == null) {
            Toast.makeText(context, "Please select your role", Toast.LENGTH_SHORT).show()
            isValid = false
        }
        return isValid
    }
}
