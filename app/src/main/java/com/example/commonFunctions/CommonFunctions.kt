package com.example.commonFunctions

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.viewbinding.ViewBinding
import com.example.lamp.R

class CommonFunctions {
//    companion object{
//        lateinit var mediaRecorder: MediaRecorder
//         fun  voiceRecord(viewBinding: ViewBinding){
//
//            var isRecording=false
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
//        }
//
//        private fun stopRecording() {
//            mediaRecorder.stop()
//            mediaRecorder.release()
//
//        }
//
//        private fun startRecording() {
//            var recordPath:String?=requireActivity().getExternalFilesDir("/")?.absolutePath
//            var recordFile="fileName.3gp"
//            mediaRecorder= MediaRecorder()
//            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
//            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
//            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//            mediaRecorder.setOutputFile(recordPath+"/"+recordFile)
//            mediaRecorder.prepare()
//            mediaRecorder.start()
//        }
//
//        @RequiresApi(Build.VERSION_CODES.M)
//        fun checkPermissions():Boolean{
//            return context?.checkSelfPermission(Manifest.permission.RECORD_AUDIO)== PackageManager.PERMISSION_GRANTED
//        }
//
//    }

}