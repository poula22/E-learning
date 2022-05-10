package com.example.lamp.ui.student.student_features_page.recitation.recite_words

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureReciteWordsCheckBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_features_page.recitation.recite_words.reciteWordsRV.ReciteWordsCheckAdapter

class ReciteWordsCheckFragment(var selector:Int=2):Fragment() {
    lateinit var viewBinding:FragmentFeatureReciteWordsCheckBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_feature_recite_words_check,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() {
        viewBinding.reciteWordsRecycleView.adapter=ReciteWordsCheckAdapter(TestData.RECITATIONWORD,selector)
    }
}