package com.example.lamp.ui.parent.parent_children_page.children_recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.ui.student.student_website_page.websites_recycler_view.WebSitesAdapter

class ChildrenAdapter(var children:MutableList<String>?=null): RecyclerView.Adapter<ChildrenAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var childName: TextView =view.findViewById(R.id.child_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.item_parent_child,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var child=children?.get(position)
        holder.childName.text=child
    }

    override fun getItemCount(): Int =children?.size ?:0
}