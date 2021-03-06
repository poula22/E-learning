package com.example.lamp.ui.student.student_features_page.recitation.recite_words.reciteWordsRV

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lamp.R
import com.example.lamp.databinding.ItemFeatureReciteWordsBinding

class ReciteWordsAdapter(var wordsList:List<ReciteWordsItem>?) :RecyclerView.Adapter<ReciteWordsAdapter.ViewHolder>(){

    class ViewHolder(var viewDataBinding:ItemFeatureReciteWordsBinding):RecyclerView.ViewHolder(viewDataBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewBinding:ItemFeatureReciteWordsBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_feature_recite_words,parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item= wordsList?.get(position)
        holder.viewDataBinding.item=item
        if (position%2==0){
            holder.viewDataBinding.arabicText.setBackgroundResource(R.color.white)
            holder.viewDataBinding.englishText.setBackgroundResource(R.color.white)
        }
        else{
            holder.viewDataBinding.arabicText.setBackgroundResource(R.color.pink)
            holder.viewDataBinding.englishText.setBackgroundResource(R.color.pink)
        }
    }

    override fun getItemCount(): Int =wordsList?.size ?: 0

}