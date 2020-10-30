package com.example.stars.ui.base

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

open class BaseFragment<T : ViewDataBinding> : Fragment() {
    lateinit var binding: T

    fun setViewModelWithDataBinding(inflater: LayoutInflater, res: Int): View {
        binding = DataBindingUtil.inflate(
            inflater, res, null, false
        )
        binding.executePendingBindings()
        binding.lifecycleOwner = this
        return binding.getRoot()
    }



}
