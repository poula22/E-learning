package com.example.lamp.ui.parent.parent_communicate_page.communicate_recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemParentCommunicateBinding


class TeacherAdapter(var teachers : List<TeacherItem>?=null): RecyclerView.Adapter<TeacherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemParentCommunicateBinding: ItemParentCommunicateBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.item_parent_communicate,parent,false)
        return ViewHolder(itemParentCommunicateBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = teachers?.get(position)
        holder.itemParentCommunicateBinding.teacherName.text = item?.teacherName
        holder.itemParentCommunicateBinding.teacherPhone.text = item?.phone
        holder.itemParentCommunicateBinding.teacherEmail.text=item?.email
        holder.itemParentCommunicateBinding.teacherFbProfile.text=item?.facebookProfile
    }

    override fun getItemCount(): Int {
        return teachers?.size ?:0;
    }


    class ViewHolder(val itemParentCommunicateBinding: ItemParentCommunicateBinding) : RecyclerView.ViewHolder(itemParentCommunicateBinding.root){

    }

}