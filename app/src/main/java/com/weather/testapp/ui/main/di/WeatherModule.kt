package com.weather.testapp.ui.main.di

import com.weather.testapp.data.online.API
import com.weather.testapp.data.online.presenter.weather.WeatherListener
import com.weather.testapp.ui.main.WeatherPresenter
import com.weather.testapp.data.online.presenter.weather.WeatherPresenter as presenters
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class WeatherModule {

    @Provides
    @WeatherScope
    fun provideAPI(retrofit: Retrofit): API {
        return retrofit.create(API::class.java)
    }

    @Provides
    @WeatherScope
    fun provideWeather(presenter: presenters<WeatherListener>): WeatherPresenter<WeatherListener> {
        return presenter
    }
}