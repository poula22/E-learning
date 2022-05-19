package com.example.lamp.ui.student.student_features_page.ocr

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.commonFunctions.CommonFunctions
import com.example.lamp.databinding.FragmentFeatureOcrBinding
import com.github.dhaval2404.imagepicker.ImagePicker


class OcrFragment : Fragment() {
    lateinit var viewBinding: FragmentFeatureOcrBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            com.example.lamp.R.layout.fragment_feature_ocr,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewBinding.iconImage.setOnClickListener {
//            CommonFunctions.imagePick(this, requireActivity())
            ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
//                .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .saveDir(requireActivity().getExternalFilesDir(Environment.DIRECTORY_DCIM)!!)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }

        viewBinding.cardDocument.setOnClickListener {
            CommonFunctions.uploadDoc(requireActivity())
        }

        viewBinding.copyOutputBtn.setOnClickListener {
            CommonFunctions.copyTextToClipboard(viewBinding.paragraphInput.text.toString(),requireContext())
        }

    }


    lateinit var imgProfile: ImageView
    lateinit var mProfileUri: Uri
    private val startForProfileImageResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!

                mProfileUri = fileUri
                imgProfile.setImageURI(fileUri)
                viewBinding.paragraphInput.setText(fileUri.toString())
                Log.v("fileUri", fileUri.toString())
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }


//    fun openActivityForResult() {
//
//        //Instead of startActivityForResult use this one
//        val intent = Intent(requireContext(), requireActivity()::class.java)
//        someActivityResultLauncher.launch(intent)
//    }
//
//
////Instead of onActivityResult() method use this one
//
//    //Instead of onActivityResult() method use this one
//    var someActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult(),
//        object : ActivityResultCallback<ActivityResult?>() {
//            override fun onActivityResult(result: ActivityResult) {
//                val data = result.data
//                if (result.resultCode == Activity.RESULT_OK) {
//                    // Here, no request code
//
//                    //  Image Uri will not be null for RESULT_OK
//                    val fileUri = data?.data!!
//
//                    mProfileUri = fileUri
//                    imgProfile.setImageURI(fileUri)
//                    Toast.makeText(context, imgProfile.toString(), Toast.LENGTH_SHORT).show()
//                } else if (result.resultCode == ImagePicker.RESULT_ERROR) {
//                    Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
//                }
//
//
//            }
//
//
//        })
}

