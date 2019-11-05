package com.weather.testapp.data.local.sql.weather

import com.weather.testapp.data.model.ListsWeather
import com.weather.testapp.data.model.WeathersModel

interface WeatherSQLPresenter {
    fun onSaveData(data: WeathersModel, listener: WeatherSQL.Listener)
    fun isAvailable(): Boolean
    fun getAllData(): WeathersModel
}