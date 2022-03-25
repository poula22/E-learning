package com.example.lamp.ui.parent.parent_communicate_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.FragmentParentCommunicateBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.parent.parent_communicate_page.communicate_recycler_view.TeacherAdapter

class CommunicateFragment:Fragment() {
    lateinit var fragmentParentCommunicateBinding: FragmentParentCommunicateBinding
    lateinit var teacherInfoAdapter: TeacherAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentParentCommunicateBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_parent_communicate,container,false)
        return fragmentParentCommunicateBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        teacherInfoAdapter=TeacherAdapter(TestData.TEACHERS)
        fragmentParentCommunicateBinding.parentCommunicateRecyclerView.adapter=teacherInfoAdapter
    }
}