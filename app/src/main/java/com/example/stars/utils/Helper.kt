package com.example.stars.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.stars.R
import com.example.stars.network.service.BASE_IMAGE_URL

open class DataBindingHelper{
    companion object {
        @BindingAdapter("setResImage")
        @JvmStatic
        open fun setResImage(image: ImageView, path: String) {
            Log.d("djdjdjdjdjdjdj",BASE_IMAGE_URL+path)
             Glide.with(image.context).load(BASE_IMAGE_URL+path).override(250)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .into(image);
        }
    }
}