package com.example.stars.ui.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.stars.network.repository.DataRepoManger

open class BaseViewModel:ViewModel() {
    val repository: DataRepoManger = DataRepoManger()
    var isLoading = ObservableField(false)
}