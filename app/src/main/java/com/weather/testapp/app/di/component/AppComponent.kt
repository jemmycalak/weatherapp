package com.weather.testapp.app.di.component

import com.weather.testapp.app.di.module.DataLocalModule
import com.weather.testapp.app.di.module.PresenterModule
import com.weather.testapp.app.di.module.RetrofitModule
import com.weather.testapp.app.di.scope.ApplicationScope
import com.weather.testapp.data.local.sql.weather.WeatherSQLPresenter
import com.weather.testapp.utils.network.NetworkChecker
import dagger.Component
import retrofit2.Retrofit

@ApplicationScope
@Component(modules = [RetrofitModule::class, PresenterModule::class, DataLocalModule::class])
interface AppComponent {
    fun getRetrofit():Retrofit
    fun getManager(): WeatherSQLPresenter
    fun getNetwork():NetworkChecker
}