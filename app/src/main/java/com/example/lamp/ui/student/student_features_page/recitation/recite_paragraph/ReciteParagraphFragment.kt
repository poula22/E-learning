package com.example.lamp.ui.student.student_features_page.recitation.recite_paragraph

import android.app.Activity
import android.content.Intent
import android.media.MediaRecorder
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.commonFunctions.CommonFunctions
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureReciteParagraphBinding
import java.util.*

class ReciteParagraphFragment : Fragment() {
    lateinit var viewBinding: FragmentFeatureReciteParagraphBinding
    lateinit var mediaRecorder: MediaRecorder
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_feature_recite_paragraph,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewBinding.btnRecite.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .addToBackStack("")
                .replace(
                    R.id.student_fragment_tab,
                    ReciteParagraphCheckFragment(viewBinding.paragraphInput.text.toString())
                )
                .commit()
        }
        viewBinding.cardImage.setOnClickListener {
            CommonFunctions.imagePick(this)
        }

        var isRecording = false
        viewBinding.cardVoice.setOnClickListener {
//            if (isRecording){
//                CommonFunctions.voiceRecord(mediaRecorder,viewBinding.cardVoice,requireActivity(),isRecording)
//                isRecording=false
//            }
//            else{
//                mediaRecorder=MediaRecorder()
//                CommonFunctions.voiceRecord(mediaRecorder,viewBinding.cardVoice,requireActivity(),isRecording)
//                isRecording=true
//            }
            var intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US.language)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "speech to text")
            try {
                startForVoiceResult.launch(intent)

            } catch (ex: Exception) {
                Log.v("error::::::", ex.message.toString())
            }
        }

        viewBinding.cardDocument.setOnClickListener {
            CommonFunctions.uploadDoc(requireActivity())
        }
    }

    private val startForVoiceResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                viewBinding.paragraphInput.setText(
                    Objects.requireNonNull(
                        data?.getStringArrayListExtra(
                            RecognizerIntent.EXTRA_RESULTS
                        )!!
                    ).get(0)
                )
            }
        }
}