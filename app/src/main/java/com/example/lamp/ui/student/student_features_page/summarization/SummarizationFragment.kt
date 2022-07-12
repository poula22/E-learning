package com.example.lamp.ui.student.student_features_page.summarization

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.ExternalStorageWithMicAccessFragment
import com.example.domain.model.SummarizationTextRequestDTO
import com.example.domain.model.SummarizationUrlRequestDTO
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureSummarizationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class SummarizationFragment : ExternalStorageWithMicAccessFragment() {
    lateinit var viewBinding: FragmentFeatureSummarizationBinding
    lateinit var mediaRecorder: MediaRecorder
    lateinit var viewModel: SummarizationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SummarizationViewModel::class.java)
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
        viewBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_feature_summarization, container, false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscribeToLiveData()
    }

    override fun sendText(text: String) {
        viewBinding.paragraphInput.setText(text)
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
        var outputText = ""
        viewModel.SummarizationLiveData.observe(viewLifecycleOwner) {
            for (x in it.sentences!!.indices) {
                outputText += it.sentences!![x]
                outputText += "\n"
            }
            viewBinding.textOutputText.setText(outputText)
        }


    }

    override fun onDetach() {
        super.onDetach()
        val bottomNavigationView: BottomNavigationView =
            requireActivity().findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.isVisible = true
    }

    private fun initViews() {
        viewBinding.cardImage.setOnClickListener {
//            ImagePicker.with(this)
//                .crop()                    //Crop image(Optional), Check Customization for more option
////                .compress(1024)			//Final image size will be less than 1 MB(Optional)
////                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//                .start()
            imagePick()

        }

        var isRecording = false
        viewBinding.cardVoice.setOnClickListener {

//            var colorRose=viewBinding.cardVoice.getContext().getResources().getColor(R.color.light_rose);
//            var colorRed=viewBinding.cardVoice.getContext().getResources().getColor(R.color.red);
//            if (isRecording){
//                stopRecording()
//                isRecording=false
//
//                viewBinding.cardVoice.setCardBackgroundColor(colorRose)
//            }
//            else{
//                if (checkPermissions()){
//                    startRecording()
//                    viewBinding.cardVoice.setCardBackgroundColor(colorRed)
//                }
//                else{
//                    var arr:Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)
//                    ActivityCompat.requestPermissions(requireActivity(),arr,21)
//                }
//                isRecording=true
//            }
            voiceRecognition()
        }
        viewBinding.cardDocument.setOnClickListener {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                val intentDocument = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
//                    addCategory(Intent.CATEGORY_OPENABLE)
//                    type = "*/*"
//                    putExtra(
//                        Intent.EXTRA_MIME_TYPES, arrayOf(
//                            "application/pdf",
//                            "application/doc",
//                            "application/docx",
//                            "text/plain"
//                        )
//                    )
//                }
//                startActivityForResult(intentDocument, ImagePicker.REQUEST_CODE)
//            }
            uploadDoc()
        }

        viewBinding.summarizeBtn.setOnClickListener {
            if (viewBinding.paragraphInput.text.toString().isNotEmpty() &&
                viewBinding.url.text.toString().isNotEmpty()
            ) {
                viewBinding.url.error = "You Should only enter Text or Url"
            } else if (viewBinding.paragraphInput.text.toString().isEmpty() &&
                viewBinding.url.text.toString().isEmpty()
            ) {
                viewBinding.paragraphInput.error = "You Should enter Text or Url"
            } else {
                var text = SummarizationTextRequestDTO(
                    viewBinding.paragraphInput.text.toString(),
                    viewBinding.sentenceNumber.text.toString()
                )
                var url = SummarizationUrlRequestDTO(
                    viewBinding.url.text.toString(),
                    viewBinding.sentenceNumber.text.toString()
                )
                if (viewBinding.paragraphInput.text.toString().isNotEmpty()) {
                    viewModel.getSummarizationFromText(text)
                } else {
                    viewModel.getSummarizationFromUrl(url)
                }
            }
        }


    }

    private fun stopRecording() {
        mediaRecorder.stop()
        mediaRecorder.release()

    }

    private fun startRecording() {
        var recordPath: String? = requireActivity().getExternalFilesDir("/")?.absolutePath
        var recordFile = "fileName.3gp"
        mediaRecorder = MediaRecorder()
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(recordPath + "/" + recordFile)
        mediaRecorder.prepare()
        mediaRecorder.start()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkPermissions(): Boolean {
        return context?.checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
    }
}