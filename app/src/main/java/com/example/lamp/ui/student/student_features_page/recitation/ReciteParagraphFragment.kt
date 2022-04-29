package com.example.lamp.ui.student.student_features_page.recitation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentReciteParagraphBinding

class ReciteParagraphFragment:Fragment() {
    lateinit var viewBinding:FragmentReciteParagraphBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_recite_paragraph,container,false)
        return viewBinding.root
    }
}