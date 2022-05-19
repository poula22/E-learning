package com.example.commonFunctions

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requireViewById
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.example.lamp.R
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*

class CommonFunctions {


    companion object {
        fun test(fragment: Fragment):ByteArray?{
            var result1:ByteArray?=null
            fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                val resultCode = result.resultCode
                val data = result.data

                if (resultCode == Activity.RESULT_OK) {
                    //Image Uri will not be null for RESULT_OK
                    val fileUri = data?.data!!

                    var inputStream = fragment.requireActivity().contentResolver.openInputStream(fileUri)
                    var byteArray = inputStream?.readBytes()
                    result1=byteArray
//                    viewModel.getData(byteArray!!)
                } else if (resultCode == ImagePicker.RESULT_ERROR) {
                    Toast.makeText(fragment.requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(fragment.requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
                }
            }
            return result1
        }
        fun imagePick(fragment: Fragment) {
            ImagePicker
                .with(fragment)
                .crop()                    //Crop image(Optional), Check Customization for more option
//                .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .saveDir(fragment.requireActivity().getExternalFilesDir(Environment.DIRECTORY_DCIM)!!)
                .start()
        }


        fun uploadDoc(activity: FragmentActivity) {
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

        fun checkPermissions(context: Context): Boolean {
            return context?.checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
        }


        val calendar: Calendar = Calendar.getInstance()

        @SuppressLint("SetTextI18n")
        fun showDatePicker(edText: EditText, context: Context) {

            val datePicker = DatePickerDialog(
                context,
                { view, year, month, dayOfMonth ->
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.YEAR, year)
                    edText.setText("" + dayOfMonth + "/" + month.plus(1) + "/" + year)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }


        fun copyTextToClipboard(text: String, context: Context) {
            val clipboardManager =
                ContextCompat.getSystemService(
                    context,
                    ClipboardManager::class.java
                ) as ClipboardManager
            val clipData = ClipData.newPlainText("text", text)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(context, "Course code copied to clipboard", Toast.LENGTH_LONG).show()
        }


        fun onBackPressed(
            activity: FragmentActivity,
            lifecycleOwner: LifecycleOwner,
            context: Context
        ) {
            activity.onBackPressedDispatcher.addCallback(
                lifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        MaterialAlertDialogBuilder(context)
                            .setTitle("Do you want to save?")
//                        .setMessage("")
                            .setNeutralButton("Cancel") { dialog, which ->
                                // Respond to neutral button press
                            }
                            .setNegativeButton("Discard") { dialog, which ->
                                Toast.makeText(context, "Changes discarded", Toast.LENGTH_SHORT)
                                    .show()
                                activity.supportFragmentManager.popBackStack()
                            }
                            .show()

                    }
                })
        }

    }
}