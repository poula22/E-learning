package com.example.lamp.ui.student.student_features_page.recitation.recite_words

import android.media.MediaRecorder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.ExternalStorageWithMicAccessFragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureReciteWordsBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_features_page.recitation.recite_words.reciteWordsRV.ReciteWordsAdapter


class ReciteWordsFragment : ExternalStorageWithMicAccessFragment() {
    lateinit var viewBinding: FragmentFeatureReciteWordsBinding
    lateinit var mediaRecorder: MediaRecorder
    lateinit var viewModel: ReciteWordsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReciteWordsViewModel::class.java)
    }

    override fun showProgressBar() {
        viewBinding.greyBackground.visibility = View.VISIBLE
        viewBinding.progressBar.visibility = View.VISIBLE
    }

    override fun resultListener(byteArray: ByteArray) {
        viewModel.getData(byteArray)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_feature_recite_words,
                container,
                false
            )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.liveData.observe(
            viewLifecycleOwner
        ) {
            val builder = StringBuilder()
            for (pageResult in it.analyzeResult().readResults()) {
                for (line in pageResult.lines()) {
                    builder.append(line.text())
                    builder.append("\n")
                }
            }

            // Hide progress bar when response is received
            viewBinding.greyBackground.visibility = View.GONE
            viewBinding.progressBar.visibility = View.GONE
            requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
//            viewBinding.paragraphInput.setText(builder)
        }
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
//            CommonFunctions.imagePick(this)
            imagePick()
        }

        var isRecording = false
        viewBinding.cardVoice.setOnClickListener {
//            if (isRecording) {
//                CommonFunctions.voiceRecord(
//                    mediaRecorder,
//                    viewBinding.cardVoice,
//                    requireActivity(),
//                    isRecording
//                )
//                isRecording = false
//            } else {
//                mediaRecorder = MediaRecorder()
//                CommonFunctions.voiceRecord(
//                    mediaRecorder,
//                    viewBinding.cardVoice,
//                    requireActivity(),
//                    isRecording
//                )
//                isRecording = true
//            }
            voiceRecognition()

        }
        viewBinding.cardDocument.setOnClickListener {
//            CommonFunctions.uploadDoc(requireActivity())
            uploadDoc()

        }


    }
    override fun sendText(text: String){

    }

}