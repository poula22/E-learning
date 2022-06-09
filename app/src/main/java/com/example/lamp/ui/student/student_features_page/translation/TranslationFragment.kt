package com.example.lamp.ui.student.student_features_page.translation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.commonFunctions.ExternalStorageWithMicAccessFragment
import com.example.data.api.translateapi.Language
import com.example.data.api.translateapi.TranslateAPI
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureTranslationBinding
import com.example.lamp.ui.student.student_features_page.summarization.SummarizationViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.ImagePicker.Companion.REQUEST_CODE
import com.google.android.material.bottomnavigation.BottomNavigationView


class TranslationFragment : ExternalStorageWithMicAccessFragment() {

    lateinit var viewBinding: FragmentFeatureTranslationBinding
    lateinit var viewModel: TranslationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TranslationViewModel::class.java)
    }

    override fun showProgressBar() {
        viewBinding.greyBackground.visibility = View.VISIBLE
        viewBinding.progressBar.visibility = View.VISIBLE
    }

    override fun resultListener(byteArray: ByteArray) {
        viewModel.getData(byteArray)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_feature_translation, container, false
        )
        return viewBinding.root
    }

    override fun onDetach() {
        super.onDetach()
        val bottomNavigationView: BottomNavigationView =
            requireActivity().findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.isVisible = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscribeToLiveData()
    }
    private fun initViews(){
        viewBinding.cardImage.setOnClickListener {
//            ImagePicker.with(this)
//                .crop()                    //Crop image(Optional), Check Customization for more option
////                .compress(1024)			//Final image size will be less than 1 MB(Optional)
////                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//                .start()
            imagePick()

        }


        viewBinding.cardDocument.setOnClickListener {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                val intentDocument = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
//                    addCategory(Intent.CATEGORY_OPENABLE)
//                    type = "*/*"
//                    putExtra(
//                        Intent.EXTRA_MIME_TYPES, arrayOf(
//                            "application/pdf",
//                            "application/doc",
//                            "application/docx",
//                            "text/plain"
//                        )
//                    )
//                }
//                startActivityForResult(intentDocument, REQUEST_CODE)
//            }
            uploadDoc()
        }


        viewBinding.cardVoice.setOnClickListener {
            voiceRecognition()
        }


        val languages = arrayOf(
            Language.ARABIC,
            Language.ENGLISH,
            Language.FRENCH,
            Language.GERMAN,
            Language.HINDI,
            Language.INDONESIAN,
            Language.ITALIAN,
            Language.JAPANESE,
            Language.KOREAN,
            Language.PORTUGUESE,
            Language.RUSSIAN,
            Language.SPANISH,
            Language.TURKISH,
            Language.VIETNAMESE,
            Language.CHINESE_SIMPLIFIED,
            Language.CHINESE_TRADITIONAL,
            Language.THAI,
            Language.INDONESIAN,
            Language.MALAY,
            Language.POLISH,
            Language.PORTUGUESE,
            Language.ROMANIAN,
            Language.BULGARIAN,
            Language.CATALAN,
            Language.CROATIAN,
            Language.CZECH,
            Language.DANISH,
            Language.DUTCH,
            Language.FINNISH,
            Language.GREEK,
            Language.HUNGARIAN,
            Language.LATVIAN,
            Language.LITHUANIAN,
            Language.NORWEGIAN,
            Language.PERSIAN,
            Language.ROMANIAN,
            Language.SLOVAK,
            Language.SLOVENIAN,
            Language.SWEDISH,
            Language.UKRAINIAN,
            Language.WELSH,
            Language.YIDDISH,
            Language.BASQUE,
            Language.BENGALI,
            Language.CATALAN,
            Language.CROATIAN,
        )
        val arrayAdapter =
            ArrayAdapter(requireContext(), R.layout.item_language, languages)
        viewBinding.autoComplete.setAdapter(arrayAdapter)

        viewBinding.translateBtn.setOnClickListener {
            val translateApi = TranslateAPI(
                Language.AUTO_DETECT,
                languages[arrayAdapter.getPosition(viewBinding.autoComplete.text.toString())],
                viewBinding.textInputText.text.toString()
            )
            translateApi.setTranslateListener(object : TranslateAPI.TranslateListener {
                override fun onSuccess(result: String) {
                    viewBinding.textOutputText.setText(result)
                }

                override fun onFailure(error: String) {
                    Toast.makeText(requireContext(), "Please Enter Text", Toast.LENGTH_SHORT).show()
                }
            })

        }
    }
    override fun sendText(text: String){
       viewBinding.textInputText.setText(text)
    }
    private fun subscribeToLiveData() {
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
            viewBinding.textInputText.setText(builder)
        }
    }
}