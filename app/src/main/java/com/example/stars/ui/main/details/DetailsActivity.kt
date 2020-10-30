package com.example.stars.ui.main.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.stars.R
import com.example.stars.databinding.ActivityDetailsBinding
import com.example.stars.ui.base.BaseActivity
import org.koin.android.ext.android.inject

class DetailsActivity : BaseActivity<ActivityDetailsBinding,DetailsViewModel>() {
    val viewModel:DetailsViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setViewModelWithDataBinding(viewModel,R.layout.activity_details)
        val id= intent.extras?.getString("ID")
        viewModel.getDetails(id!!)
        getimages(id)
        binding.toolbarBack.setOnClickListener(
            {
                onBackPressed()
            }
        )
    }

    private fun getimages(id: String) {
        val gLay= GridLayoutManager(this,2)
        binding.imagesList.layoutManager=gLay
        binding.imagesList.adapter=adapter
        viewModel.getImagesList(id).observe(this, Observer {

        })
    }
}
