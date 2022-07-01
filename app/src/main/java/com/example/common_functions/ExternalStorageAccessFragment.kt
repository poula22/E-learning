package com.example.common_functions

import android.os.Environment
import com.github.dhaval2404.imagepicker.ImagePicker

abstract class ExternalStorageAccessFragment : DocumentAccessFragment() {
    fun imagePick() {
        ImagePicker.with(this)
            .crop()                    //Crop image(Optional), Check Customization for more option
            .saveDir(requireActivity().getExternalFilesDir(Environment.DIRECTORY_DCIM)!!)
            .createIntent(startForImageResult::launch)
    }

}