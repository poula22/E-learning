package com.example.lamp.ui.teacher.profile_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherHomeBinding
import com.example.lamp.databinding.FragmentTeacherProfileBinding

class TeacherProfileFragment:Fragment() {
    lateinit var teacherProfileBinding: FragmentTeacherProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        teacherProfileBinding = DataBindingUtil.inflate<FragmentTeacherProfileBinding>(inflater,R.layout.fragment_teacher_profile,container,false)
        return teacherProfileBinding.root
    }
}