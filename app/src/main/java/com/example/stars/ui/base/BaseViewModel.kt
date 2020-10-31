package com.example.stars.ui.base

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stars.R
import com.example.stars.network.repository.DataRepoManger
import com.example.stars.network.service.HandleNet

open class BaseViewModel : ViewModel(), HandleNet.RemoteErrorEmitter {
    val repository: DataRepoManger = DataRepoManger()
    var isLoading = ObservableField(false)
    var toastMsg = MutableLiveData<Int>(0)


    override fun onError(msg: String) {


    }

    override fun onError(errorType: HandleNet.ErrorType) {
        Log.d("djdjjd77jjdjdjdjj_", "${errorType}")
        if (errorType.equals(HandleNet.ErrorType.NETWORK) || errorType.equals(HandleNet.ErrorType.TIMEOUT)) {
            toastMsg.postValue(R.string.networkError)
        }
    }
}