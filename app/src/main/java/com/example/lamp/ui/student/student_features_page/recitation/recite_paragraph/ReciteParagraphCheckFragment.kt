package com.example.lamp.ui.student.student_features_page.recitation.recite_paragraph

import android.media.MediaRecorder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.common_functions.CommonFunctions
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureReciteParagraphCheckBinding

class ReciteParagraphCheckFragment(var paragraph: String) : Fragment() {
    lateinit var viewBinding: FragmentFeatureReciteParagraphCheckBinding
    lateinit var mediaRecorder: MediaRecorder
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_feature_recite_paragraph_check, container, false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        var isRecording = false
        viewBinding.cardVoice.setOnClickListener {
            if (isRecording) {
                CommonFunctions.voiceRecord(
                    mediaRecorder,
                    viewBinding.cardVoice,
                    requireActivity(),
                    isRecording
                )
                isRecording = false
            } else {
                mediaRecorder = MediaRecorder()
                CommonFunctions.voiceRecord(
                    mediaRecorder,
                    viewBinding.cardVoice,
                    requireActivity(),
                    isRecording
                )
                isRecording = true
            }
        }
        viewBinding.cardDocument.setOnClickListener {
            CommonFunctions.uploadDoc(requireActivity())
        }

    }

}
