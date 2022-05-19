package com.example.lamp.ui.student.student_features_page.recitation.recite_paragraph

import android.media.MediaRecorder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.commonFunctions.CommonFunctions
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureReciteParagraphBinding

class ReciteParagraphFragment:Fragment() {
    lateinit var viewBinding:FragmentFeatureReciteParagraphBinding
    lateinit var mediaRecorder: MediaRecorder
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_feature_recite_paragraph,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewBinding.btnRecite.setOnClickListener{
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .addToBackStack("")
                .replace(R.id.student_fragment_tab,
                    ReciteParagraphCheckFragment(viewBinding.paragraphInput.text.toString())
                )
                .commit()
        }
        viewBinding.cardImage.setOnClickListener {
            CommonFunctions.imagePick(this,requireActivity())

        }

        var isRecording=false
        viewBinding.cardVoice.setOnClickListener {
            if (isRecording){
                CommonFunctions.voiceRecord(mediaRecorder,viewBinding.cardVoice,requireActivity(),isRecording)
                isRecording=false
            }
            else{
                mediaRecorder=MediaRecorder()
                CommonFunctions.voiceRecord(mediaRecorder,viewBinding.cardVoice,requireActivity(),isRecording)
                isRecording=true
            }

        }
        viewBinding.cardDocument.setOnClickListener {
            CommonFunctions.uploadDoc(requireActivity())

        }

    }

}