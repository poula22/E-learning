package com.example.lamp.ui.student.student_website_page.websites_recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R

class WebSitesAdapter(var websites:MutableList<String>?=null):RecyclerView.Adapter<WebSitesAdapter.ViewHolder>() {

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var website:TextView=view.findViewById(R.id.website)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student_websites,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item=websites?.get(position)
        holder.website.text=item
    }

    override fun getItemCount(): Int =websites?.size ?:0
}