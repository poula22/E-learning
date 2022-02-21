package com.example.lamp.ui.student_home_page.features_recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.recyclerviewpracticekotlin.FeatureItem

class FeaturesRVAdapter(var featuresItemsList : List<FeatureItem>?=null,val type:Int): RecyclerView.Adapter<FeaturesRVAdapter.FeaturesItemViewHolder>() {

    val HOME_SCREEN=R.layout.item_student_home_feature_rv
    val FEATURES_SCREEN=R.layout.items_student_feature
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturesItemViewHolder {
        var screen=HOME_SCREEN
        if(type== 1){
            screen=FEATURES_SCREEN
        }
        var view =LayoutInflater.from(parent.context).inflate(screen,parent,false)
        return FeaturesItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeaturesItemViewHolder, position: Int) {
        val item = featuresItemsList?.get(position)
        holder.featureName.text = item?.featureName
        holder.image.setImageResource(item?.featureImageID ?:0)
    }

    override fun getItemCount(): Int {
        return featuresItemsList?.size ?:0;
    }


    class FeaturesItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var featureName:TextView = itemView.findViewById<TextView>(R.id.feature_name)
        var image: ImageView = itemView.findViewById<ImageView>(R.id.feature_image_view)



    }

}