package com.weather.testapp.data.online

import com.weather.testapp.BuildConfig
import com.weather.testapp.data.model.WeathersModel
import io.reactivex.Observable
import retrofit2.http.GET

interface API {
    @GET("data/2.5/forecast?q=jakarta&lang=id&units=metric&appid="+BuildConfig.APIKEY)
    fun getWeather():Observable<WeathersModel>
}