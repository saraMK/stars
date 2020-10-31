package com.example.stars.ui.main.displayImage

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.ActivityCompat
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.stars.R
import com.example.stars.network.service.ORIGIN_BASE_IMAGE_URL
import com.example.stars.ui.base.BaseViewModel
import com.example.stars.utils.DataBindingHelper
import com.example.stars.utils.SaveImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL


class DisplayImageViewModel() : BaseViewModel(), Callback {
    var imag = ObservableField("")
    lateinit var activity: Activity

    var showPermissionDialog = MutableLiveData<Boolean>(false)

    fun downloadImage() {
        if (checkPermissionForExternalStorage(activity)) {

            if (!DataBindingHelper.isNetworkAvailable(activity)){
                toastMsg.postValue(R.string.networkError)

            }
            else{
            onToastMsg(R.string.startingDownlod)
            val urlImage = URL(ORIGIN_BASE_IMAGE_URL + imag.get())
            CoroutineScope(Dispatchers.IO).launch {
                val connection = urlImage.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.content
                val inputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)
                saveToInternalStorage(bitmap)
            }
            }
        } else {
            requestPermissionForExternalStorage()
        }
    }

    private fun saveToInternalStorage(bitmapImage: Bitmap) {
        SaveImage().save(activity, bitmapImage, this)
    }


    fun requestPermissionForExternalStorage() {
        showPermissionDialog.postValue(true)
    }

    fun checkPermissionForExternalStorage(activity: Activity?): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            false
        } else {
            true
        }
    }


    override fun onToastMsg(msg: Int) {
        CoroutineScope(Dispatchers.Main).launch { toastMsg.postValue(msg) }
    }


}

interface Callback {
    fun onToastMsg(msg: Int)
}