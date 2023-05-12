package com.example.lamp.ui.student.student_features_page.ocr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.CommonFunctions
import com.example.common_functions.ExternalStorageAccessFragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureOcrBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class OcrFragment : ExternalStorageAccessFragment() {
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
        subscirbeToLiveData()
        initViews()
    }

    private fun initViews() {
        viewBinding.cardImage.setOnClickListener {
//            CommonFunctions.imagePick(this,startForImageResult)
            imagePick()
        }

        viewBinding.cardDocument.setOnClickListener {
//            startForImageResult.launch(CommonFunctions.uploadDoc(this.requireActivity()))
            uploadDoc()
        }


        viewBinding.copyOutputBtn.setOnClickListener {
            CommonFunctions.copyTextToClipboard(
                viewBinding.paragraphInput.text.toString(),
                requireContext()
            )
        }

    }

    override fun showProgressBar() {
        viewBinding.greyBackground.visibility = View.VISIBLE
        viewBinding.progressBar.visibility = View.VISIBLE
    }

    override fun resultListener(byteArray: ByteArray) {
        viewModel.getData(byteArray)
    }


//    val startForImageResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
//            val resultCode = result.resultCode
//            val data = result.data
//
//            if (resultCode == Activity.RESULT_OK) {
//                //Image Uri will not be null for RESULT_OK
//                val fileUri = data?.data!!
//
//                var inputStream = requireActivity().contentResolver.openInputStream(fileUri)
//                var byteArray = inputStream?.readBytes()
//
//                // Show progress bar when request is in progress
//                viewBinding.greyBackground.visibility = View.VISIBLE
//                viewBinding.progressBar.visibility = View.VISIBLE
//                requireActivity().window.setFlags(
//                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
//                )
//                ///
//                viewModel.getData(byteArray!!)
//
//            } else if (resultCode == ImagePicker.RESULT_ERROR) {
//                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
//                    .show()
//            } else {
//                Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show()
//            }
//        }


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

