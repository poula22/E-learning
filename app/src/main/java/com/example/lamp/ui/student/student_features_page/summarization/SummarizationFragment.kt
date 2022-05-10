package com.example.lamp.ui.student.student_features_page.summarization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureSummarizationBinding

class SummarizationFragment : Fragment() {

    lateinit var viewBinding: FragmentFeatureSummarizationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_feature_summarization,
            container,
            false
        )
        return viewBinding.root
    }


}