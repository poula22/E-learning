package com.example.commonFunctions

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.github.dhaval2404.imagepicker.ImagePicker

abstract class DocumentAccessFragment:Fragment() {
    var filePath:String?=null
    abstract fun showProgressBar()
    abstract fun resultListener(byteArray: ByteArray)
    fun uploadDoc() {
        startForImageResult.launch(
            Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "*/*"
                putExtra(
                    Intent.EXTRA_MIME_TYPES, arrayOf(
                        "application/pdf"
                    )
                )
            }
        )

    }

    val startForImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data
            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!
                filePath=fileUri.path
                var inputStream = requireActivity().contentResolver.openInputStream(fileUri)
                var byteArray = inputStream?.readBytes()


                byteArray?.let {
                    // Show progress bar when request is in progress
                    showProgressBar()
                    requireActivity().window.setFlags(
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    )
                    ///
                    resultListener(byteArray)
                }


            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        Log.v("fragment:","here")
    }


}