package com.example.lamp.ui.student.student_features_page.ocr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.commonFunctions.CONSTANTS
import com.example.commonFunctions.CommonFunctions
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureOcrBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomnavigation.BottomNavigationView


class OcrFragment : Fragment() {
    lateinit var viewBinding: FragmentFeatureOcrBinding
    lateinit var viewModel: OcrViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OcrViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        viewBinding.cardImage.setOnClickListener {
//                ImagePicker.with(this)
//                .crop()                    //Crop image(Optional), Check Customization for more option
//                .saveDir(requireActivity().getExternalFilesDir(Environment.DIRECTORY_DCIM)!!)
//                .createIntent(startForImageResult::launch)
            CommonFunctions.imagePick(this,startForImageResult)

        }

        viewBinding.cardDocument.setOnClickListener {
//            val x =CommonFunctions.uploadDoc(requireActivity())

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
            ActivityCompat.startActivityForResult(
                requireActivity(),
                intentDocument,
                CONSTANTS.DOCUMENT_REQUEST_CODE,
                Bundle.EMPTY
            )
            startForImageResult.launch(intentDocument)
        }


        viewBinding.copyOutputBtn.setOnClickListener {
            CommonFunctions.copyTextToClipboard(
                viewBinding.paragraphInput.text.toString(),
                requireContext()
            )
        }

    }


    val startForImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!

                var inputStream = requireActivity().contentResolver.openInputStream(fileUri)
                var byteArray = inputStream?.readBytes()

                // Show progress bar when request is in progress
                viewBinding.greyBackground.visibility = View.VISIBLE
                viewBinding.progressBar.visibility = View.VISIBLE
                requireActivity().window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )
                ///
                viewModel.getData(byteArray!!)

            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show()
            }
        }


    private fun subscirbeToLiveData() {
        viewModel.liveData.observe(
            viewLifecycleOwner
        ) {
            val builder = StringBuilder()
            for (pageResult in it.analyzeResult().readResults()) {
                for (line in pageResult.lines()) {
                    builder.append(line.text())
                    builder.append("\n")
                }
            }

            // Hide progress bar when response is received
            viewBinding.greyBackground.visibility = View.GONE
            viewBinding.progressBar.visibility = View.GONE
            requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            viewBinding.paragraphInput.setText(builder)
        }
    }

    override fun onDetach() {
        super.onDetach()
        val bottomNavigationView: BottomNavigationView =
            requireActivity().findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.isVisible = true
    }

}

