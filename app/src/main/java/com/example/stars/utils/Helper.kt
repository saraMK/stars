package com.example.stars.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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
        open fun setResImage(image: ImageView, path: String?) {
             if (!path.isNullOrEmpty())
                Glide.with(image.context).load(BASE_IMAGE_URL+path!!)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(image)
            else image.setImageResource(R.drawable.placeholder)
        }

        @BindingAdapter("setOriginImage")
        @JvmStatic
        open fun setOriginImage(image: ImageView, path: String?) {

            if (!path.isNullOrEmpty())
            Glide.with(image.context).load(ORIGIN_BASE_IMAGE_URL+path)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .into(image)
            else image.setImageResource(R.drawable.placeholder)
        }

        open fun isNetworkAvailable(context: Context?): Boolean {
            if (context == null) return false
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    when {
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                            return true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                            return true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                            return true
                        }
                    }
                }
            } else {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    return true
                }
            }
            return false
        }
    }
}