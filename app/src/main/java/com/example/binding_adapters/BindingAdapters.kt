package com.example.binding_adapters
import android.media.MediaRecorder
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:imageId")
fun loadImageFromId(imageView: ImageView,imageId:Int){
    imageView.setImageResource(imageId)
}

lateinit var mediaRecorder: MediaRecorder
