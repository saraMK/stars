package com.example.stars.network.service

import com.example.stars.models.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApis {
    @GET("person/popular")
    suspend fun getPopularPerson(@Query("page") page: Int): ResponseModel<List<PeopleModel>>
    @GET("person/{id}")
    suspend fun getPerson(@Path("id") id: String): PersonDetailsModel
    @GET("person/{id}/images")
    suspend fun getImages(@Path("id") id: String): ImagesModel
}