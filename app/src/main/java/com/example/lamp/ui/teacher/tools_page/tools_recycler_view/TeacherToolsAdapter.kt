package com.example.lamp.ui.teacher.tools_page.tools_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemStudentWebsitesBinding

class TeacherToolsAdapter(var tools: MutableList<String>? = null) :
    RecyclerView.Adapter<TeacherToolsAdapter.ViewHolder>() {

    lateinit var viewBinding: ItemStudentWebsitesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewBinding = DataBindingUtil.inflate<ItemStudentWebsitesBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_student_websites,
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var tools = tools?.get(position)
        holder.viewBinding.item = tools
    }

    override fun getItemCount(): Int = tools?.size ?: 0

    class ViewHolder(val viewBinding: ItemStudentWebsitesBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

    }

}