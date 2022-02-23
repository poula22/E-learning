package com.example.lamp.ui.parent.parent_communicate_page.communicate_recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R


class TeacherAdapter(var teachers : List<TeacherItem>?=null): RecyclerView.Adapter<TeacherAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_parent_communicate,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = teachers?.get(position)
        holder.teacherName.text = item?.teacherName
        holder.phone.text = item?.phone
        holder.teacherEmail.text=item?.email
        holder.teacherFbProfile.text=item?.facebookProfile
    }

    override fun getItemCount(): Int {
        return teachers?.size ?:0;
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var teacherName: TextView = itemView.findViewById<TextView>(R.id.teacher_name)
        var phone: TextView = itemView.findViewById<TextView>(R.id.teacher_phone)
        var teacherEmail: TextView = itemView.findViewById<TextView>(R.id.teacher_email)
        var teacherFbProfile: TextView =itemView.findViewById<TextView>(R.id.teacher_fb_profile)
    }

}