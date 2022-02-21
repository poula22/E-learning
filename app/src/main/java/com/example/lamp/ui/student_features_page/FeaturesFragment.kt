package com.example.lamp.ui.student_features_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R

import com.example.lamp.ui.student_home_page.features_recycler_view.FeaturesRVAdapter

class FeaturesFragment:Fragment() {
    lateinit var featuresRecyclerView: RecyclerView
    lateinit var featuresRVAdapter: FeaturesRVAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_features,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        featuresRecyclerView=requireView().findViewById(R.id.student_features_recycler_view)
        featuresRVAdapter= FeaturesRVAdapter(type=1)
        featuresRecyclerView.adapter=featuresRVAdapter
    }
}