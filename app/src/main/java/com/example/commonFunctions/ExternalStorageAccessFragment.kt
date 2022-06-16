package com.example.commonFunctions

import android.app.Activity
import android.content.Intent
import android.os.Environment
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import java.util.*

abstract class ExternalStorageAccessFragment:DocumentAccessFragment() {
    fun imagePick() {
        ImagePicker.with(this)
            .crop()                    //Crop image(Optional), Check Customization for more option
            .saveDir(requireActivity().getExternalFilesDir(Environment.DIRECTORY_DCIM)!!)
            .createIntent(startForImageResult::launch)
    }

}