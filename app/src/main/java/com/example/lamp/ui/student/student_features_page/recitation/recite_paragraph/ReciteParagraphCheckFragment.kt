package com.example.lamp.ui.student.student_features_page.recitation.recite_paragraph

import android.media.MediaRecorder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CommonFunctions
import com.example.domain.model.RecitationParagraphRequestDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureReciteParagraphCheckBinding

class ReciteParagraphCheckFragment(var paragraph: String) : Fragment() {
    lateinit var viewBinding: FragmentFeatureReciteParagraphCheckBinding
    lateinit var mediaRecorder: MediaRecorder
    lateinit var viewModel: ReciteParagraphViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReciteParagraphViewModel::class.java)
    }

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
        subscribeToLiveData()
        initViews()
    }

    private fun subscribeToLiveData() {
        viewModel.recitationLiveData.observe(viewLifecycleOwner) {
            viewBinding.resultPercentage.text = it.similarity?.times(100).toString() + "%"
            if (it.similarity?.times(100).toString().toDouble() > 50) {
                viewBinding.resultPercentage.setTextColor(resources.getColor(R.color.green, null))
            } else {
                viewBinding.resultPercentage.setTextColor(resources.getColor(R.color.red, null))
            }
        }
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

        viewBinding.btnCheck.setOnClickListener {
            var request =
                RecitationParagraphRequestDTO(paragraph, viewBinding.paragraphInput.text.toString())
            viewModel.getSimilarity(request)
        }


    }

}
