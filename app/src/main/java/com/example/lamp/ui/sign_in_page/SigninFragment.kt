package com.example.lamp.ui.sign_in_page

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lamp.R
import com.example.lamp.databinding.FragmentSigninBinding
import com.example.lamp.ui.parent.ParentContainerFragment
import com.example.lamp.ui.sign_up_page.SignUpFragment
import com.example.lamp.ui.student.StudentContainerFragment
import com.example.lamp.ui.teacher.TeacherContainerFragment
import java.util.regex.Pattern

class SigninFragment : Fragment() {
    lateinit var viewBinding: FragmentSigninBinding
    lateinit var viewModel: SigninViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SigninViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_signin, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        viewBinding.childImg.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StudentContainerFragment())
                .addToBackStack("")
                .commit()
        }
        viewBinding.parentImg.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ParentContainerFragment())
                .addToBackStack("")
                .commit()
        }
        viewBinding.teacherImg.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TeacherContainerFragment())
                .addToBackStack("")
                .commit()
        }






        viewBinding.buttonSignin.setOnClickListener {
            //signin
            validate()
        }

        viewBinding.buttonSignUp.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignUpFragment())
                .addToBackStack("")
                .commit()
        }
    }

    private fun validate(): Boolean {
        var isValid = true
        if (viewBinding.password.editText?.text.toString().isEmpty()) {
            viewBinding.password.error = "Password is required"
            isValid = false
        } else {
            viewBinding.password.error = null
        }

        if (viewBinding.email.editText?.text.toString().isEmpty()) {
            viewBinding.email.error = "Email is required"
        } else if (!Pattern.matches(
                "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
                viewBinding.email.editText?.text.toString()
            )
        ) {
            viewBinding.email.error = "Incorrect email address"
            isValid = false
        } else {
            viewBinding.email.error = null
        }
        return isValid
    }
}