package com.example.projekat

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("load")
fun loadImage(imageView: ImageView, url: String) {
    imageView.load(url)
}