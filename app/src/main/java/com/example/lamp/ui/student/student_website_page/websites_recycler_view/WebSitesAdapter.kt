package com.example.lamp.ui.student.student_website_page.websites_recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemStudentWebsitesBinding

class WebSitesAdapter(var websites:MutableList<String>?=null):RecyclerView.Adapter<WebSitesAdapter.ViewHolder>() {

    class ViewHolder(val viewBinding: ItemStudentWebsitesBinding):RecyclerView.ViewHolder(viewBinding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding:ItemStudentWebsitesBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context)
            ,R.layout.item_student_websites,parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item=websites?.get(position)
        holder.viewBinding.item=item
        holder.viewBinding.websiteLink.load(item!!)
    }

    override fun getItemCount(): Int =websites?.size ?:0
}