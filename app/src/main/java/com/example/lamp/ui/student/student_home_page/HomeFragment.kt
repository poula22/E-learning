package com.example.lamp.ui.student.student_home_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentHomeBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_features_page.ocr.OcrFragment
import com.example.lamp.ui.student.student_features_page.recitation.RecitationFragment
import com.example.lamp.ui.student.student_features_page.summarization.SummarizationFragment
import com.example.lamp.ui.student.student_features_page.translation.TranslationFragment
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CoursesRVAdapter
import com.example.lamp.ui.student.student_home_page.features_recycler_view.FeatureItem
import com.example.lamp.ui.student.student_home_page.features_recycler_view.FeaturesRVAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment() {
    lateinit var viewBinding: FragmentStudentHomeBinding
    lateinit var coursesRVAdapter: CoursesRVAdapter
    lateinit var featuresRVAdapter: FeaturesRVAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_student_home, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        coursesRVAdapter = CoursesRVAdapter(TestData.COURSES, type = 0)
        viewBinding.coursesRecyclerView.adapter = coursesRVAdapter
        featuresRVAdapter = FeaturesRVAdapter(TestData.FEATURES, type = 0)
        featuresRVAdapter.onFeatureClickListener = object : FeaturesRVAdapter.FeatureClickListener {
            override fun onFeatureClick(pos: Int, item: FeatureItem) {
                if (pos==0){
                    requireActivity().supportFragmentManager.beginTransaction().addToBackStack("")
                        .replace(R.id.student_fragment_tab, TranslationFragment()).commit()
                }
                else if(pos==1){
                    requireActivity().supportFragmentManager.beginTransaction().addToBackStack("")
                        .replace(R.id.student_fragment_tab, SummarizationFragment()).commit()
                }
                else if (pos==2){
                    requireActivity().supportFragmentManager.beginTransaction().addToBackStack("")
                        .replace(R.id.student_fragment_tab, RecitationFragment()).commit()
                }
                else if (pos==3){
                    requireActivity().supportFragmentManager.beginTransaction().addToBackStack("")
                        .replace(R.id.student_fragment_tab,OcrFragment()).commit()
                }
                val bottomNavigationView: BottomNavigationView =
                    requireActivity().findViewById(R.id.bottom_navigation_view)
                bottomNavigationView.isVisible = false
            }

        }
        viewBinding.featureRecyclerView.adapter = featuresRVAdapter
    }

    interface onItemClickListener {
        fun onItemClick(pos: Int)
    }
}