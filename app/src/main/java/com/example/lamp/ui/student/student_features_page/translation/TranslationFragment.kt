package com.example.lamp.ui.student.student_features_page.translation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.data.api.translateapi.Language
import com.example.data.api.translateapi.TranslateAPI
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureTranslationBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.ImagePicker.Companion.REQUEST_CODE
import com.google.android.material.bottomnavigation.BottomNavigationView


class TranslationFragment : Fragment() {

    lateinit var viewBinding: FragmentFeatureTranslationBinding

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

        viewBinding.cardImage.setOnClickListener {
            ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
//                .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()

        }


        viewBinding.cardDocument.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
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
                startActivityForResult(intentDocument, REQUEST_CODE)
            }
        }


        viewBinding.cardVoice.setOnClickListener {


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
}