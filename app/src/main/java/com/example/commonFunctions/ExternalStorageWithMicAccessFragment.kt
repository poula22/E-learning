package com.example.commonFunctions

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import java.util.*

abstract class ExternalStorageWithMicAccessFragment :ExternalStorageAccessFragment(){

    abstract fun sendText(text: String)

    fun voiceRecognition(){
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

    private val startForVoiceResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data
            if (resultCode == Activity.RESULT_OK) {
                var text= Objects.requireNonNull(
                    data?.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS
                    )!!
                ).get(0)
                sendText(text)

            }
        }
}