package com.example.lamp.ui.parent.parent_children_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.FragmentParentChildrenBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.parent.parent_children_page.children_recycler_view.ChildrenAdapter
import com.example.lamp.ui.student.student_website_page.websites_recycler_view.WebSitesAdapter

class ChildrenFragment:Fragment() {
    lateinit var parentChildrenBinding: FragmentParentChildrenBinding
    lateinit var adapter:ChildrenAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parentChildrenBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_parent_children,container,false)
        return parentChildrenBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        adapter= ChildrenAdapter(TestData.CHILDREN)
        parentChildrenBinding.parentChildrenRecyclerView.adapter=adapter
    }
}