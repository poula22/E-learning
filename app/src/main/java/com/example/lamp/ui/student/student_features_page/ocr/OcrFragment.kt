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
import androidx.lifecycle.ViewModelProvider
import com.example.commonFunctions.CommonFunctions
import com.example.lamp.databinding.FragmentFeatureOcrBinding
import com.example.lamp.ui.sign_up_page.SignUpViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream


class OcrFragment : Fragment() {
    lateinit var viewBinding: FragmentFeatureOcrBinding
    lateinit var viewModel: OcrViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProvider(this).get(OcrViewModel::class.java)
    }
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
        subscirbeToLiveData()

    }

    private fun initViews() {
        viewBinding.iconImage.setOnClickListener {
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

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!

                var inputStream = requireActivity().contentResolver.openInputStream(fileUri)
                var byteArray = inputStream?.readBytes()
                viewModel.getData(byteArray!!)

            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }



    fun subscirbeToLiveData(){
        viewModel.liveData.observe(viewLifecycleOwner
        ) {
            val builder = StringBuilder()
            for (pageResult in it.analyzeResult().readResults()) {
                for (line in pageResult.lines()) {
                    builder.append(line.text())
                    builder.append(" ")
                }
            }
            viewBinding.paragraphInput.setText(builder)


        }




    }


}

