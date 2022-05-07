package com.example.lamp.ui.parent.parent_children_page.children_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemParentChildBinding

class ChildrenAdapter(var children: MutableList<String>? = null) :
    RecyclerView.Adapter<ChildrenAdapter.ViewHolder>() {

    lateinit var itemParentChildBinding: ItemParentChildBinding

    class ViewHolder(val itemParentChildBinding: ItemParentChildBinding) :
        RecyclerView.ViewHolder(itemParentChildBinding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemParentChildBinding = DataBindingUtil.inflate<ItemParentChildBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_parent_child,
            parent,
            false
        )
        return ViewHolder(itemParentChildBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var child = children?.get(position)
        holder.itemParentChildBinding.childName.text = child
    }

    override fun getItemCount(): Int = children?.size ?: 0
}