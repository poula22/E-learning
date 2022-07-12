package com.example.lamp.ui.student.student_features_page.translation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.common_functions.ExternalStorageWithMicAccessFragment
import com.example.data.api.translateapi.Language
import com.example.data.api.translateapi.TranslateAPI
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureTranslationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadOperationResult


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
        OCRData(byteArray)
    }

    private fun OCRData(byteArray: ByteArray) {
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

    private fun initViews() {
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
            Language.AFRIKAANS,
            Language.ALBANIAN,
            Language.ARABIC,
            Language.ARMENIAN,
            Language.AZERBAIJANI,
            Language.BASQUE,
            Language.BELARUSIAN,
            Language.BENGALI,
            Language.BULGARIAN,
            Language.CATALAN,
            Language.CHINESE,
            Language.CROATIAN,
            Language.CZECH,
            Language.DANISH,
            Language.DUTCH,
            Language.ENGLISH,
            Language.ESTONIAN,
            Language.FILIPINO,
            Language.FINNISH,
            Language.FRENCH,
            Language.GALICIAN,
            Language.GEORGIAN,
            Language.GERMAN,
            Language.GREEK,
            Language.GUJARATI,
            Language.HAITIAN_CREOLE,
            Language.HEBREW,
            Language.HINDI,
            Language.HUNGARIAN,
            Language.ICELANDIC,
            Language.INDONESIAN,
            Language.IRISH,
            Language.ITALIAN,
            Language.JAPANESE,
            Language.KANNADA,
            Language.KOREAN,
            Language.LATIN,
            Language.LATVIAN,
            Language.LITHUANIAN,
            Language.MACEDONIAN,
            Language.MALAY,
            Language.MALTESE,
            Language.NORWEGIAN,
            Language.PERSIAN,
            Language.POLISH,
            Language.PORTUGUESE,
            Language.ROMANIAN,
            Language.RUSSIAN,
            Language.SERBIAN,
            Language.SLOVAK,
            Language.SLOVENIAN,
            Language.SPANISH,
            Language.SWAHILI,
            Language.SWEDISH,
            Language.TAMIL,
            Language.TELUGU,
            Language.THAI,
            Language.TURKISH,
            Language.UKRAINIAN,
            Language.URDU,
            Language.VIETNAMESE,
            Language.WELSH,
            Language.YIDDISH,
        )

        val arrayAdapter =
            ArrayAdapter(requireContext(), R.layout.item_language, languages)
        viewBinding.autoComplete.setAdapter(arrayAdapter)

        viewBinding.translateBtn.setOnClickListener {
            translateData(languages, arrayAdapter)
        }
    }

    private fun translateData(languages: Array<String>, arrayAdapter: ArrayAdapter<String>) {
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

    override fun sendText(text: String) {
        tranlateText(text)
    }

    private fun tranlateText(text: String) {
        viewBinding.textInputText.setText(text)
    }

    private fun subscribeToLiveData() {

        viewModel.liveData.observe(
            viewLifecycleOwner
        ) {
            getTextFromResult(it)
        }
    }

    private fun getTextFromResult(result: ReadOperationResult) {
        try {
            val builder = StringBuilder()
            for (pageResult in result.analyzeResult().readResults()) {
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
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Please Enter Text", Toast.LENGTH_SHORT).show()
        }
    }
}