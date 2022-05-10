package com.example.lamp.ui.student.student_features_page.sumarization

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureSummarizationBinding
import com.github.dhaval2404.imagepicker.ImagePicker

class SummarizationFragment:Fragment() {
    lateinit var viewBinding: FragmentFeatureSummarizationBinding
    lateinit var mediaRecorder: MediaRecorder
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_feature_summarization
            ,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewBinding.cardImage.setOnClickListener {
            ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
//                .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()

        }

        var isRecording=false
        viewBinding.cardVoice.setOnClickListener {

            var colorRose=viewBinding.cardVoice.getContext().getResources().getColor(R.color.light_rose);
            var colorRed=viewBinding.cardVoice.getContext().getResources().getColor(R.color.red);
            if (isRecording){
                stopRecording()
                isRecording=false

                viewBinding.cardVoice.setCardBackgroundColor(colorRose)
            }
            else{
                if (checkPermissions()){
                    startRecording()
                    viewBinding.cardVoice.setCardBackgroundColor(colorRed)
                }
                else{
                    var arr:Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)
                    ActivityCompat.requestPermissions(requireActivity(),arr,21)
                }
                isRecording=true
            }
        }
        viewBinding.cardDocument.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                val intentDocument = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                    addCategory(Intent.CATEGORY_OPENABLE)
                    type = "*/*"
                    putExtra(
                        Intent.EXTRA_MIME_TYPES, arrayOf(
                            "application/pdf",
                            "application/doc",
                            "application/docx",
                            "text/plain"
                        )
                    )
                }
                startActivityForResult(intentDocument, ImagePicker.REQUEST_CODE)
            }
        }

    }
    private fun stopRecording() {
        mediaRecorder.stop()
        mediaRecorder.release()

    }

    private fun startRecording() {
        var recordPath:String?=requireActivity().getExternalFilesDir("/")?.absolutePath
        var recordFile="fileName.3gp"
        mediaRecorder= MediaRecorder()
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(recordPath+"/"+recordFile)
        mediaRecorder.prepare()
        mediaRecorder.start()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkPermissions():Boolean{
        return context?.checkSelfPermission(Manifest.permission.RECORD_AUDIO)== PackageManager.PERMISSION_GRANTED
    }
}