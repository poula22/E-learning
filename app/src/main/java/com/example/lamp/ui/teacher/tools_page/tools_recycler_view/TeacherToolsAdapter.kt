package com.example.lamp.ui.teacher.tools_page.tools_recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R

class TeacherToolsAdapter(var tools:MutableList<String>?=null):RecyclerView.Adapter<TeacherToolsAdapter.ViewHolder> (){
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tool:TextView=view.findViewById(R.id.website)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_student_websites,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var tool=tools?.get(position)
        holder.tool.text=tool
    }

    override fun getItemCount(): Int =tools?.size ?:0

}