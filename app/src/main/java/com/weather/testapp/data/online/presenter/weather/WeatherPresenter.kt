package com.weather.testapp.data.online.presenter.weather

import android.util.Log
import com.weather.testapp.data.model.ListsWeather
import com.weather.testapp.data.model.WeathersModel
import com.weather.testapp.data.online.API
import com.weather.testapp.data.online.Presenter
import com.weather.testapp.ui.main.WeatherPresenter
import com.weather.testapp.utils.converter.TimeConverter
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class WeatherPresenter<V : WeatherListener>
@Inject
constructor() : Presenter<V>(), Observer<WeathersModel>, WeatherPresenter<V> {

    @Inject lateinit var api: API

    override fun getWeather() {
        subcribe(api.getWeather(), this)
    }

    override fun onComplete() {}

    override fun onSubscribe(d: Disposable) {}

    override fun onNext(t: WeathersModel) {
        val timeConverter = TimeConverter()

        /*
        * to filter data by hours
        * */
        val dataHourWeathers = arrayListOf<ListsWeather>()
        val date1 = timeConverter.EEEEddMMMMyyyy(t.list[0].dt_txt)
        for (i in 0..(t.list.size-1)){
            val date2 = timeConverter.EEEEddMMMMyyyy(t.list[i].dt_txt)
            if (date1 ==date2){
                dataHourWeathers.add(t.list[i])
            } else break
        }

        /*
        * get current weather by this moment
        * */
        var currentWeather : ListsWeather?=null
        val now = timeConverter.YYYYmmddHHmmssToString(timeConverter.CurrentDateTime())
        for (i in 0..(dataHourWeathers.size-1)){
            val nDate = timeConverter.YYYYmmddHHmmssToString(dataHourWeathers[i].dt_txt)
            if (now.before(nDate)){
                currentWeather = dataHourWeathers[i]
                break
            }else if (i == (dataHourWeathers.size-1)) currentWeather = dataHourWeathers[i]
        }

        /*
        * to filter data by date and at 12PM
        * */
        val dataDayWeather = arrayListOf<ListsWeather>()
        var date4=""
        for(i in 0..(t.list.size-1)){
            val hours = timeConverter.HHamPM(t.list[i].dt_txt)

            if (date4 == "") date4 = timeConverter.EEEEddMMMMyyyy(t.list[i].dt_txt)
            val date5 = timeConverter.EEEEddMMMMyyyy(t.list[i].dt_txt)

            if(date4 != date5 && hours == "12 PM") {
                dataDayWeather.add(t.list[i])
                date4= timeConverter.EEEEddMMMMyyyy(t.list[i].dt_txt)
            } else continue
        }

        view.onUpdate(t, dataHourWeathers, dataDayWeather, currentWeather!!)
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }
}