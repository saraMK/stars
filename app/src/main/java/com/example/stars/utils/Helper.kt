package com.example.stars.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.stars.R
import com.example.stars.network.service.BASE_IMAGE_URL
import com.example.stars.network.service.ORIGIN_BASE_IMAGE_URL

open class DataBindingHelper{
    companion object {
        @BindingAdapter("setResImage")
        @JvmStatic
        open fun setResImage(image: ImageView, path: String) {
            if (path!=null&&!path.isEmpty())
                Glide.with(image.context).load(BASE_IMAGE_URL+path)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(image)
            else image.setImageResource(R.drawable.placeholder)
        }

        @BindingAdapter("setOriginImage")
        @JvmStatic
        open fun setOriginImage(image: ImageView, path: String) {
            Log.d("djdjjdjdjjdjd","path")
            Log.d("djdjjdjdjjdjd",path)
            if (path!! !=null&&!path!!.isEmpty())
            Glide.with(image.context).load(ORIGIN_BASE_IMAGE_URL+path)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .into(image)
            else image.setImageResource(R.drawable.placeholder)
        }
    }
}