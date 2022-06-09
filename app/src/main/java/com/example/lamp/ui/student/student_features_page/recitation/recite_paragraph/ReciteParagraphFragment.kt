package com.example.lamp.ui.student.student_features_page.recitation.recite_paragraph

import android.media.MediaRecorder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.commonFunctions.ExternalStorageWithMicAccessFragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureReciteParagraphBinding

class ReciteParagraphFragment : ExternalStorageWithMicAccessFragment() {
    lateinit var viewBinding: FragmentFeatureReciteParagraphBinding
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
        subscribeToLiveData()
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
//            CommonFunctions.imagePick(this)
            imagePick()
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

            //////////////////////////////////////////////////////////////////////

//            var intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
//            intent.putExtra(
//                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
//            )
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US.language)
//            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "speech to text")
//            try {
//                startForVoiceResult.launch(intent)
//
//            } catch (ex: Exception) {
//                Log.v("error::::::", ex.message.toString())
//            }
            voiceRecognition()
        }

        viewBinding.cardDocument.setOnClickListener {
//            CommonFunctions.uploadDoc(requireActivity())
            uploadDoc()
        }
    }

    override fun sendText(text: String){
        viewBinding.paragraphInput.setText(text)
    }

    override fun showProgressBar() {
        viewBinding.greyBackground.visibility = View.VISIBLE
        viewBinding.progressBar.visibility = View.VISIBLE
    }

    override fun resultListener(byteArray: ByteArray) {
        viewModel.getData(byteArray)
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
            viewBinding.paragraphInput.setText(builder)
        }
    }



//    private val startForVoiceResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
//            val resultCode = result.resultCode
//            val data = result.data
//
//            if (resultCode == Activity.RESULT_OK) {
//                viewBinding.paragraphInput.setText(
//                    Objects.requireNonNull(
//                        data?.getStringArrayListExtra(
//                            RecognizerIntent.EXTRA_RESULTS
//                        )!!
//                    ).get(0)
//                )
//            }
//        }
}