package com.example.lamp.ui.student.student_features_page.recitation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentReciteWordsBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_features_page.recitation.reciteWords.recitation.ReciteWordsCheckFragment
import com.example.lamp.ui.student.student_features_page.recitation.reciteWords.recitation.reciteWordsRV.ReciteWordsAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView


class ReciteWordsFragment:Fragment() {
    lateinit var viewBinding: FragmentReciteWordsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=
            DataBindingUtil.inflate(inflater, R.layout.fragment_recite_words,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        var selector=2
        viewBinding.reciteWordsRecycleView.adapter=ReciteWordsAdapter(TestData.WORDs)
        viewBinding.arabicText.setOnClickListener{view->
            view.setBackgroundResource(R.color.blue)
            viewBinding.englishText.setBackgroundResource(R.color.white)
            selector=1
        }

        viewBinding.englishText.setOnClickListener{view->
            view.setBackgroundResource(R.color.blue)
            viewBinding.arabicText.setBackgroundResource(R.color.white)
            selector=2
        }

        viewBinding.btnRecite.setOnClickListener{
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .addToBackStack("")
                .replace(R.id.student_fragment_tab,ReciteWordsCheckFragment(selector))
                .commit()

        }


    }

}