package com.example.lamp.ui.features_recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.recyclerviewpracticekotlin.FeatureItem

class FeaturesRVAdapter(var featuresItemsList : List<FeatureItem>): RecyclerView.Adapter<FeaturesRVAdapter.FeaturesItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturesItemViewHolder {
        var view =LayoutInflater.from(parent.context).inflate(R.layout.item_feature_rv,parent,false)
        return FeaturesItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeaturesItemViewHolder, position: Int) {
        val item = featuresItemsList[position]
        holder.featureName.text = item.featureName
        holder.image.setImageResource(item.featureImageID)
    }

    override fun getItemCount(): Int {
        return featuresItemsList.size;
    }


    class FeaturesItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var featureName:TextView = itemView.findViewById<TextView>(R.id.feature_name)
        var image: ImageView = itemView.findViewById<ImageView>(R.id.feature_image_view)



    }

}