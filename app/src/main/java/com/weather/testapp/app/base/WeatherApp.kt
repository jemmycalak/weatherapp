package com.weather.testapp.app.base

import android.app.Application
import com.weather.testapp.app.di.component.AppComponent
import com.weather.testapp.app.di.component.DaggerAppComponent
import com.weather.testapp.app.di.module.NetworkModule
import com.weather.testapp.app.di.module.RetrofitModule

class WeatherApp : Application(){

    lateinit var appComponent:AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .retrofitModule(RetrofitModule())
            .networkModule(NetworkModule())
            .build()
    }
}