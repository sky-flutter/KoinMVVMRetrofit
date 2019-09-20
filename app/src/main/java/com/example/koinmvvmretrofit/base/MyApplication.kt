package com.example.koinmvvmretrofit.base

import android.app.Application
import com.example.koinmvvmretrofit.di.apiModule
import com.example.koinmvvmretrofit.di.appModule
import com.example.koinmvvmretrofit.di.networkModule
import com.example.koinmvvmretrofit.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(appModule, networkModule, apiModule, viewModelModule))
        }
    }
}