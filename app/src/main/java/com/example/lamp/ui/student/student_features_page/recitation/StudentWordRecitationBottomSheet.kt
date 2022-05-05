package com.example.lamp.ui.student.student_features_page.recitation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.lamp.R
import com.example.lamp.databinding.FragmentReciteWordsBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StudentWordRecitationBottomSheet : BottomSheetDialogFragment() {


    lateinit var studentWordRecitationBinding: FragmentReciteWordsBottomSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        studentWordRecitationBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_recite_words_bottom_sheet,
            container,
            false
        )
        return studentWordRecitationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews() {
        studentWordRecitationBinding.voiceIcon.setOnClickListener {

        }

        studentWordRecitationBinding.rightIcon.setOnClickListener {

        }

        studentWordRecitationBinding.leftIcon.setOnClickListener {

        }


    }
}
