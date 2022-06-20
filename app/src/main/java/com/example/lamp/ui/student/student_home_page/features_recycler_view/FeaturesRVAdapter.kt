package com.example.lamp.ui.student.student_home_page.features_recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemStudentHomeFeatureRvBinding
import com.example.lamp.databinding.ItemStudentFeatureBinding

class FeaturesRVAdapter(var featuresItemsList : List<FeatureItem>?=null,val type:Int): RecyclerView.Adapter<FeaturesRVAdapter.FeaturesItemViewHolder>() {

    val HOME_SCREEN=R.layout.item_student_home_feature_rv
    val FEATURES_SCREEN=R.layout.item_student_feature
    lateinit var viewDataBinding: ViewDataBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturesItemViewHolder {
        if(type== 1){
            var screen=FEATURES_SCREEN
            viewDataBinding=DataBindingUtil.inflate<ItemStudentFeatureBinding>(LayoutInflater.from(parent.context),screen,parent,false)
        }
        else if (type==0){
            var screen=HOME_SCREEN
            viewDataBinding=DataBindingUtil.inflate<ItemStudentHomeFeatureRvBinding>(LayoutInflater.from(parent.context),screen,parent,false)
        }
        return FeaturesItemViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: FeaturesItemViewHolder, position: Int) {
        val item = featuresItemsList?.get(position)
        if (type==0){
            var viewBinding=holder.viewDataBinding as ItemStudentHomeFeatureRvBinding
            viewBinding.item=item
            if (onFeatureClickListener!=null && item!=null){
                viewBinding.card.setOnClickListener{
                    onFeatureClickListener!!.onFeatureClick(position,item)
                }
            }

        }
        else{
            var viewBinding=holder.viewDataBinding as ItemStudentFeatureBinding
            viewBinding.item=item
            if (onFeatureClickListener!=null && item!=null){
                viewBinding.card.setOnClickListener{
                    onFeatureClickListener!!.onFeatureClick(position,item)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return featuresItemsList?.size ?:0;
    }


    class FeaturesItemViewHolder(val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root){

    }

    var onFeatureClickListener:FeatureClickListener?=null
    interface FeatureClickListener{
        fun onFeatureClick(pos:Int,item:FeatureItem)
    }

}