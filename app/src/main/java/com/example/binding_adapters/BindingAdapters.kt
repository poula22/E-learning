package com.example.binding_adapters
import android.widget.ImageView
import androidx.databinding.BindingAdapter
@BindingAdapter("app:imageId")
fun loadImageFromId(imageView: ImageView,imageId:Int){
    imageView.setImageResource(imageId)
}

