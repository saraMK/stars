package com.example.stars.ui.main.displayImage

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.stars.R
import com.example.stars.databinding.DisplayImageActivityBinding
import com.example.stars.ui.base.BaseActivity
import com.example.stars.ui.main.details.DetailsViewModel
import org.koin.android.ext.android.inject

class DisplayImageActivity : BaseActivity<DisplayImageActivityBinding,DisplayImageViewModel>() {
    val viewModel= DisplayImageViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val path=intent.extras?.getString("path")
        setViewModelWithDataBinding(viewModel,R.layout.display_image_activity)
        viewModel.imag.set(path)
        Log.d("djdjjdjdjdndnnd0",viewModel.imag.get())

        binding.backBtn.setOnClickListener({
            onBackPressed()
        })
    }


}