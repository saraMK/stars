package com.example.stars.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.stars.models.PeopleModel
import com.example.stars.models.ResponseModel
import com.example.stars.network.service.HandleNet
import com.example.stars.ui.base.BaseViewModel

class HomeViewModel : BaseViewModel() {

    var page: Int = 1
    var loadMore = MutableLiveData<Boolean>(true)
    fun getList(): LiveData<ResponseModel<List<PeopleModel>>?> {
        isLoading.set(true)
        return liveData {
            try {
                val response = HandleNet().safeApiCall(this@HomeViewModel) {
                    repository.getPopularPerson(page)
                }
                emit(response)
                if (page == response?.total_pages)
                    loadMore.postValue(false)
                else loadMore.postValue(true)

                page++
                isLoading.set(false)
            } catch (e: Exception) {
                isLoading.set(false)
            }
        }


    }

    override fun onError(msg: String) {
        loadMore.postValue(false)
    }

    override fun onError(errorType: HandleNet.ErrorType) {
        super.onError(errorType)
         if (errorType.equals(HandleNet.ErrorType.NETWORK) || errorType.equals(HandleNet.ErrorType.TIMEOUT)) {
          loadMore.postValue(true)
        }
    }
}
