package com.example.lamp.ui.student.student_features_page.recitation.recite_words

import android.media.MediaRecorder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.commonFunctions.CommonFunctions
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureReciteWordsBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_features_page.recitation.recite_words.reciteWordsRV.ReciteWordsAdapter


class ReciteWordsFragment : Fragment() {
    lateinit var viewBinding: FragmentFeatureReciteWordsBinding
    lateinit var mediaRecorder: MediaRecorder
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_feature_recite_words, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        var selector = 2
        viewBinding.reciteWordsRecycleView.adapter = ReciteWordsAdapter(TestData.WORDs)
        viewBinding.arabicText.setOnClickListener { view ->
            view.setBackgroundResource(R.color.blue)
            viewBinding.englishText.setBackgroundResource(R.color.white)
            selector = 1
        }

        viewBinding.englishText.setOnClickListener { view ->
            view.setBackgroundResource(R.color.blue)
            viewBinding.arabicText.setBackgroundResource(R.color.white)
            selector = 2
        }

        viewBinding.btnRecite.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .addToBackStack("")
                .replace(R.id.student_fragment_tab, ReciteWordsCheckFragment(selector))
                .commit()

        }

        viewBinding.cardImage.setOnClickListener {
            CommonFunctions.imagePick(this)

        }

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