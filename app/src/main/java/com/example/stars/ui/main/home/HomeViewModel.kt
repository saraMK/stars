package com.example.stars.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.stars.models.PeopleModel
import com.example.stars.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException

class HomeViewModel : BaseViewModel() {

    var page: Int = 1
    var loadMore = MutableLiveData<Boolean>(true)
    fun getList(): LiveData<List<PeopleModel>> {
        isLoading.set(true)
        return liveData(Dispatchers.IO) {
            try {
                val retrived = repository.getPopularPerson(page)
                Log.d("okhttp: <-- ", "${retrived}")
                emit(retrived.results)
                if (page==retrived.total_pages)
                    loadMore.postValue(false)
                else loadMore.postValue(true)

                page++
                isLoading.set(false)
            } catch (e: HttpException) {
                Log.d("djdjdjdjdjjddj", e.message)
                isLoading.set(false)
            }
        }
    }

}
