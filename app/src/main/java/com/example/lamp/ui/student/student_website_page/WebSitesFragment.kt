package com.example.lamp.ui.student.student_website_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.student.student_website_page.websites_recycler_view.WebSitesAdapter

class WebSitesFragment:Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter:WebSitesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_websites,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        recyclerView=requireView().findViewById(R.id.websites_recycler_view)
        adapter= WebSitesAdapter(TestData.WEBSITES)
        recyclerView.adapter=adapter
    }
}