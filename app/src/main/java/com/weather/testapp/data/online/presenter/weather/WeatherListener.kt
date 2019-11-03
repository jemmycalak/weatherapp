package com.weather.testapp.data.online.presenter.weather

import com.weather.testapp.app.base.BaseView
import com.weather.testapp.data.model.ListsWeather
import com.weather.testapp.data.model.WeathersModel

interface WeatherListener: BaseView {
    fun onUpdate(
        t: WeathersModel,
        dataHourWeathers: ArrayList<ListsWeather>,
        dataDayWeather: ArrayList<ListsWeather>,
        currentWeather: ListsWeather
    )
}