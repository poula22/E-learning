package com.example.lamp.ui.student.student_home_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.ui.student.student_home_page.courses_recycler_view.CoursesRVAdapter
import com.example.lamp.ui.student.student_home_page.features_recycler_view.FeaturesRVAdapter


class HomeFragment: Fragment() {
    lateinit var coursesRecyclerView: RecyclerView
    lateinit var featuresRecyclerView: RecyclerView
    lateinit var coursesRVAdapter: CoursesRVAdapter
    lateinit var featuresRVAdapter: FeaturesRVAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        coursesRecyclerView=requireView().findViewById(R.id.courses_recycler_view)
        featuresRecyclerView=requireView().findViewById(R.id.children_recycler_view)
        coursesRVAdapter=CoursesRVAdapter(type=0)
        coursesRecyclerView.adapter=coursesRVAdapter
        featuresRVAdapter=FeaturesRVAdapter(type=0)
        featuresRecyclerView.adapter=featuresRVAdapter
    }
}