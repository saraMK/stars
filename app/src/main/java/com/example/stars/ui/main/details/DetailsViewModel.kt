package com.example.stars.ui.main.details

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.stars.models.PeopleModel
import com.example.stars.models.PersonDetailsModel
import com.example.stars.models.ProfileModel
import com.example.stars.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailsViewModel:BaseViewModel() {
    var name = ObservableField("")
    var date = ObservableField("")
    var info = ObservableField("")
    var img = ObservableField("")
     fun getDetails(id:String) {
        isLoading.set(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val retrived = repository.getPersonDetails(id)
                name.set(retrived.name)
                date.set(retrived.birthday)
                info.set(retrived.biography)
                img.set(retrived.profile_path)
                isLoading.set(false)
            }
            catch (e: HttpException) {
                 isLoading.set(false)
            }
        }
    }


    fun getImagesList(id:String): LiveData<List<ProfileModel>> {
        isLoading.set(true)
        return liveData(Dispatchers.IO) {
            try {
                val retrived = repository.getImages(id)
                emit(retrived.profiles)
                isLoading.set(false)
            } catch (e: HttpException) {
                isLoading.set(false)
            }
        }
    }
}