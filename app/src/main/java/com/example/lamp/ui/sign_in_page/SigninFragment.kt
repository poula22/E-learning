package com.example.lamp.ui.sign_in_page

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentSigninBinding
import com.example.lamp.ui.parent.ParentContainerFragment
import com.example.lamp.ui.student.StudentContainerFragment
import com.example.lamp.ui.teacher.TeacherContainerFragment

class SigninFragment : Fragment() {
    lateinit var viewBinding: FragmentSigninBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_signin, container, false)
        return viewBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        MicrosoftOCRTest.microsoftOcrTest()
//        QuickstartSample.authExplicit("C:\\Users\\nvenr\\Downloads\\lampitproject-a2ad7178d4b1.json")
//        QuickstartSample.test()
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
    }
}