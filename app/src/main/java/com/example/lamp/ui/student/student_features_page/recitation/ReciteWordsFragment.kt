package com.example.lamp.ui.student.student_features_page.recitation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentReciteWordsBinding

class ReciteWordsFragment:Fragment() {
    lateinit var viewBinding: FragmentReciteWordsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=
            DataBindingUtil.inflate(inflater, R.layout.fragment_recite_words,container,false)
        return viewBinding.root
    }
}