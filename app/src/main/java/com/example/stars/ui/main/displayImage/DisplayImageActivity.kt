package com.example.stars.ui.main.displayImage

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.example.stars.R
import com.example.stars.databinding.DisplayImageActivityBinding
import com.example.stars.ui.base.BaseActivity
import com.example.stars.ui.main.details.DetailsViewModel
import org.koin.android.ext.android.inject

class DisplayImageActivity : BaseActivity<DisplayImageActivityBinding,DisplayImageViewModel>() {
    val viewModel: DisplayImageViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModelWithDataBinding(viewModel,R.layout.display_image_activity)
        val path=intent.extras?.getString("path")
        viewModel.imag.set(path)
        viewModel.activity=this
        binding.backBtn.setOnClickListener({
            onBackPressed()
        })


        viewModel.showPermissionDialog.observe(this, Observer {
            if (it)
            show_permissionDialog(
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                100
            )
        })

        viewModel.toastMsg.observe(this, Observer {
            if (it!=0)
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })
    }

    fun show_permissionDialog(
        permissionsArray: Array<String?>?,
        requestID: Int
    ) {
        ActivityCompat.requestPermissions(
            this,
            permissionsArray!!,
            requestID
        )
    }
}