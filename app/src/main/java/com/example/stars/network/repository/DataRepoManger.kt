package com.example.stars.network.repository

import android.util.Log
import com.example.stars.models.PeopleModel
import com.example.stars.models.ResponseModel
import com.example.stars.network.service.NetworkApis
import com.example.stars.network.service.Resource
import com.example.stars.ui.main.home.HomeViewModel
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit

class DataRepoManger() :  KoinComponent {
      val apis: NetworkApis by inject()

    suspend fun getPopularPerson(page:Int): ResponseModel<List<PeopleModel>> {
        val data=apis.getPopularPerson(page)
        Log.d("jjdjjjdjdjdjddj55", "${data}")
        return data
    }

    suspend fun getPersonDetails(id:String)= apis.getPerson(id)
    suspend fun getImages(id:String)= apis.getImages(id)
}