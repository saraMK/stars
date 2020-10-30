package com.example.stars

import android.app.Application
 import com.example.stars.koin.viewModelModule
import com.example.stars.network.service.networkModule_

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication :Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(
                viewModelModule,networkModule_
            ))


        }
    }

}