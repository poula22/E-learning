package com.example.lamp.ui.parent.parent_children_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.parent.parent_children_page.children_recycler_view.ChildrenAdapter
import com.example.lamp.ui.student.student_website_page.websites_recycler_view.WebSitesAdapter

class ChildrenFragment:Fragment() {
    lateinit var recyclerView:RecyclerView
    lateinit var adapter:ChildrenAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parent_children,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        recyclerView=requireView().findViewById(R.id.parent_student_courses_recycler_view)
        adapter= ChildrenAdapter(TestData.CHILDREN)
        recyclerView.adapter=adapter
    }
}