package com.weather.testapp.app.di.component

import com.weather.testapp.app.di.module.PresenterModule
import com.weather.testapp.app.di.module.RetrofitModule
import com.weather.testapp.app.di.scope.ApplicationScope
import dagger.Component
import retrofit2.Retrofit

@ApplicationScope
@Component(modules = [RetrofitModule::class, PresenterModule::class])
interface AppComponent {
    fun getRetrofit():Retrofit
}