package com.example.lamp.ui.student.student_features_page.recitation

import android.Manifest
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
import com.example.lamp.R
import com.example.lamp.databinding.FragmentReciteWordsBottomSheetBinding
import com.example.lamp.ui.student.student_features_page.recitation.reciteWords.recitation.reciteWordsRV.ReciteWordsAdapter
import com.example.lamp.ui.student.student_features_page.recitation.reciteWords.recitation.reciteWordsRV.ReciteWordsItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StudentWordRecitationBottomSheet(var wordsList: List<ReciteWordsItem>?,var postion:Int) : BottomSheetDialogFragment() {


    lateinit var studentWordRecitationBinding: FragmentReciteWordsBottomSheetBinding
    lateinit var mediaRecorder: MediaRecorder
    var word:ReciteWordsItem?=wordsList?.get(postion)
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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initViews() {
        var isRecording=false
        studentWordRecitationBinding.voiceIcon.setOnClickListener {
            if (isRecording){
                stopRecording()
                isRecording=false
                studentWordRecitationBinding.voiceIconImageView.setBackgroundResource(R.color.white)
            }
            else{
                if (checkPermissions()){
                    startRecording()
                    studentWordRecitationBinding.voiceIconImageView.setBackgroundResource(R.color.pink)
                }
                else{
                    var arr:Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)
                    ActivityCompat.requestPermissions(requireActivity(),arr,21)
                }
                isRecording=true
            }
        }

        studentWordRecitationBinding.rightIcon.setOnClickListener {
            postion+=1
            word=wordsList?.get(postion)
        }

        studentWordRecitationBinding.leftIcon.setOnClickListener {
            postion-=1
            word=wordsList?.get(postion)
        }


    }

    private fun stopRecording() {
        mediaRecorder.stop()
        mediaRecorder.release()

    }

    private fun startRecording() {
        var recordPath:String?=requireActivity().getExternalFilesDir("/")?.absolutePath
        var recordFile="fileName.3gp"
        mediaRecorder=MediaRecorder()
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(recordPath+"/"+recordFile)
        mediaRecorder.prepare()
        mediaRecorder.start()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkPermissions():Boolean{
            return context?.checkSelfPermission(Manifest.permission.RECORD_AUDIO)==PackageManager.PERMISSION_GRANTED
    }
}

