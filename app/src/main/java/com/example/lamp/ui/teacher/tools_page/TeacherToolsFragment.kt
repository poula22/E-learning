package com.example.lamp.ui.teacher.tools_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.FragmentTeacherToolsBinding
import com.example.lamp.test_data.TestData
import com.example.lamp.ui.sign_in_page.SigninFragment
import com.example.lamp.ui.teacher.tools_page.tools_recycler_view.TeacherToolsAdapter


class TeacherToolsFragment : Fragment() {
    lateinit var fragmentTeacherToolsBinding: FragmentTeacherToolsBinding
    lateinit var adapter: TeacherToolsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTeacherToolsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_teacher_tools, container, false)
        return fragmentTeacherToolsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() {
        adapter = TeacherToolsAdapter(TestData.TOOLS)
        fragmentTeacherToolsBinding.toolsRecyclerView.adapter = adapter


    }

}