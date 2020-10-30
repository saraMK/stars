package com.example.stars.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModel

open class BaseActivity<T : ViewDataBinding, V : ViewModel> : AppCompatActivity() {

    lateinit  var binding: T
    lateinit var mViewModel: V
    fun setViewModelWithDataBinding(viewModel: V, res: Int) {
        binding = DataBindingUtil.setContentView(this, res)
        this.mViewModel = viewModel
        binding?.setVariable(BR.viewModel, viewModel)
        binding?.executePendingBindings()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}

