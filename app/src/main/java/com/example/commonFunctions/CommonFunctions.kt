package com.example.commonFunctions

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.lamp.R
import com.github.dhaval2404.imagepicker.ImagePicker

class CommonFunctions {
    companion object {
        fun imagePick(fragment: Fragment) {
            ImagePicker.with(fragment)
                .crop()                    //Crop image(Optional), Check Customization for more option
//                .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        fun uploadDoc(activity: FragmentActivity) {
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
                startActivityForResult(
                    activity,
                    intentDocument,
                    ImagePicker.REQUEST_CODE,
                    Bundle.EMPTY
                )
            }
        }

        fun voiceRecord(
            mediaRecorder: MediaRecorder,
            cardVoice: CardView,
            activity: FragmentActivity,
            isRecording: Boolean
        ) {
            var colorRose = cardVoice.getContext().getResources().getColor(R.color.light_rose);
            var colorRed = cardVoice.getContext().getResources().getColor(R.color.red);
            if (isRecording) {
                stopRecording(mediaRecorder)
                cardVoice.setCardBackgroundColor(colorRose)
            } else {
                if (checkPermissions(activity.baseContext)) {
                    startRecording(activity, mediaRecorder)
                    cardVoice.setCardBackgroundColor(colorRed)
                } else {
                    var arr: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)
                    ActivityCompat.requestPermissions(activity, arr, 21)
                }
            }
        }

        private fun stopRecording(mediaRecorder: MediaRecorder) {
            mediaRecorder.stop()
            mediaRecorder.release()

        }

        private fun startRecording(activity: FragmentActivity, mediaRecorder: MediaRecorder) {
            var recordPath: String? = activity.getExternalFilesDir("/")?.absolutePath
            var recordFile = "fileName.3gp"
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(recordPath + "/" + recordFile)
            mediaRecorder.prepare()
            mediaRecorder.start()
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun checkPermissions(context: Context): Boolean {
            return context?.checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
        }

    }

}