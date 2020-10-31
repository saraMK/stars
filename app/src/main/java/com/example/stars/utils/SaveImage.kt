package com.example.stars.utils

import android.Manifest
 import android.app.Activity
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.stars.R
import com.example.stars.ui.main.displayImage.Callback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

open class SaveImage {

    fun save(
        activity: Activity, bmImg: Bitmap,callback: Callback
    ) {


            val filename: File
            try {
                val path1 = Environment.getExternalStorageDirectory()
                    .toString()
                //Log.i("in save()", "after mkdir");
                val file =
                    File(path1 + "/" + activity.getString(R.string.app_name))
                if (!file.exists()) file.mkdirs()
                var DEFAULT_IMAGE_NAME =
                        System.currentTimeMillis().toString() + "_" + "fansfolio"

                filename = File(
                    file.absolutePath + "/" + DEFAULT_IMAGE_NAME
                            + ".jpg"
                )
                //Log.i("in save()", "after file");
                val out = FileOutputStream(filename)
                //Log.i("in save()", "after outputstream");
                bmImg.compress(Bitmap.CompressFormat.JPEG, 90, out)
                out.flush()
                out.close()
                val image = getImageContent(filename, DEFAULT_IMAGE_NAME, activity)
                val result = activity.contentResolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, image
                )

                var SHARE_MESSAGE = "Image is saved "
                 callback.onToastMsg(SHARE_MESSAGE)
            } catch (e: Exception) {
                e.printStackTrace()
                callback.onToastMsg("error")
            }

    }

    fun toasstMsg(msg:String, activity: Activity){
        CoroutineScope(Dispatchers.Main).launch {Toast.makeText(activity,msg,Toast.LENGTH_LONG).show()}
    }
    fun getImageContent(
        parent: File,
        imageName: String?,
        activity: Activity
    ): ContentValues {
        val image = ContentValues()
        image.put(MediaStore.Images.Media.TITLE, activity.getString(R.string.app_name))
        image.put(MediaStore.Images.Media.DISPLAY_NAME, imageName)
        image.put(
            MediaStore.Images.Media.DESCRIPTION,
            "Fb Page Galley Image - MyInnos.in | by fansfolio"
        )
        image.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis())
        image.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        image.put(MediaStore.Images.Media.ORIENTATION, 0)
        image.put(
            MediaStore.Images.ImageColumns.BUCKET_ID, parent.toString()
                .toLowerCase().hashCode()
        )
        image.put(
            MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, parent.name
                .toLowerCase()
        )
        image.put(MediaStore.Images.Media.SIZE, parent.length())
        image.put(MediaStore.Images.Media.DATA, parent.absolutePath)
        return image
    }



}