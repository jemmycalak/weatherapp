package com.weather.testapp.ui.main

import com.weather.testapp.app.base.BasePresenter
import com.weather.testapp.app.di.scope.ApplicationScope
import com.weather.testapp.data.online.presenter.weather.WeatherListener

interface WeatherPresenter<V:WeatherListener>: BasePresenter<V> {
    fun getWeather()
}