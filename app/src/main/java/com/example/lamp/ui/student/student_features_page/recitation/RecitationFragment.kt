package com.example.lamp.ui.student.student_features_page.recitation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lamp.R
import com.example.lamp.databinding.FragmentFeatureRecitationBinding
import com.example.lamp.ui.student.student_features_page.recitation.recite_paragraph.ReciteParagraphFragment
import com.example.lamp.ui.student.student_features_page.recitation.recite_words.ReciteWordsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class RecitationFragment : Fragment() {

    lateinit var viewBinding: FragmentFeatureRecitationBinding
    lateinit var tabLayout: TabLayout

    private fun initTabs(paragraphTab: TabLayout.Tab, wordsTab: TabLayout.Tab) {
        paragraphTab.text = "Paragraph"
        paragraphTab.tag = ReciteParagraphFragment()
        wordsTab.text = "Words"
        wordsTab.tag = ReciteWordsFragment()
        tabLayout.addTab(paragraphTab)
        tabLayout.addTab(wordsTab)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_feature_recitation, container, false
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
        tabLayout = viewBinding.tabLayout
        var paragraphTab = tabLayout.newTab()
        var wordsTab = tabLayout.newTab()
        initTabs(paragraphTab, wordsTab)
        tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(
                            viewBinding.recitationFragmentContainer.id,
                            tab?.tag as Fragment
                        ).commit()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(
                            viewBinding.recitationFragmentContainer.id,
                            tab?.tag as Fragment
                        ).commit()
                }


            }
        )
        tabLayout.selectTab(paragraphTab)
    }


}