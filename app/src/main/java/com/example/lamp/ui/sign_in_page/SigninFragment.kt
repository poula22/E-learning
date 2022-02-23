package com.example.lamp.ui.sign_in_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.ui.parent.ParentContainerFragment
import com.example.lamp.ui.student.StudentContainerFragment
import com.example.lamp.ui.teacher.TeacherContainerFragment

class SigninFragment:Fragment() {
    lateinit var student:ImageView
    lateinit var parent:ImageView
    lateinit var teacher:ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signin,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        student=requireView().findViewById(R.id.child_img)
        parent=requireView().findViewById(R.id.parent_img)
        teacher=requireView().findViewById(R.id.teacher_img)

        student.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,StudentContainerFragment())
                .addToBackStack("")
                .commit()
        }
        parent.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ParentContainerFragment())
                .addToBackStack("")
                .commit()
        }
        teacher.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TeacherContainerFragment())
                .addToBackStack("")
                .commit()
        }
    }
}