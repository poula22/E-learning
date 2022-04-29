package com.example.lamp.ui.student.student_website_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.FragmentStudentWebsitesBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_website_page.websites_recycler_view.WebSitesAdapter

class WebSitesFragment:Fragment() {
    lateinit var viewBinding:FragmentStudentWebsitesBinding
    lateinit var adapter:WebSitesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_student_websites,container,false)
        return viewBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        adapter= WebSitesAdapter(TestData.WEBSITES)
        viewBinding.websitesRecyclerView.adapter=adapter
    }
}