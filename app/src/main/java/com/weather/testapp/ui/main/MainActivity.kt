package com.weather.testapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.weather.testapp.BuildConfig
import com.weather.testapp.R
import com.weather.testapp.app.base.BaseView
import com.weather.testapp.app.base.WeatherApp
import com.weather.testapp.data.model.ListsWeather
import com.weather.testapp.data.model.WeathersModel
import com.weather.testapp.data.online.presenter.weather.WeatherListener
import com.weather.testapp.ui.main.di.DaggerWeatherComponent
import com.weather.testapp.ui.main.di.WeatherAdapetrModule
import com.weather.testapp.utils.adapter.recylerviewAdapter.RecyclerviewAdapter
import com.weather.testapp.utils.adapter.recylerviewAdapter.holder.DateWeatherHolder
import com.weather.testapp.utils.adapter.recylerviewAdapter.holder.HourWeatherHolder
import com.weather.testapp.utils.converter.CapitalLetter
import com.weather.testapp.utils.converter.TimeConverter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), WeatherListener, BaseView {


    @Inject
    lateinit var presenter: WeatherPresenter<WeatherListener>
    @Inject
    lateinit var adapterHour:RecyclerviewAdapter<ListsWeather, HourWeatherHolder>
    @Inject
    lateinit var adapterDate:RecyclerviewAdapter<ListsWeather, DateWeatherHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val component = (application as WeatherApp).appComponent
        DaggerWeatherComponent.builder()
            .appComponent(component)
            .weatherAdapetrModule(WeatherAdapetrModule(this))
            .build().inject(this)

        recyclerViewHours.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterHour
        }

        recyclerViewDate.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = adapterDate
        }

        presenter.onAttach(this)
        presenter.getWeather()

    }

    override fun onUpdate(
        t: WeathersModel,
        dataHourWeathers: ArrayList<ListsWeather>,
        dataDayWeather: ArrayList<ListsWeather>,
        currentWeather: ListsWeather
    ) {
        val temp = Math.round(currentWeather.main.temp).toString()
        address.text = t.city.name
        date.text = TimeConverter().EEEEddMMMMyyyy(currentWeather.dt_txt)
        celcius.text = temp+"\u00B0"
        descCloud.text = CapitalLetter().CapsSentences(currentWeather.weather[0].description)
        var img = 0
        when(currentWeather.weather[0].main){
            "Rain"-> {
                if(currentWeather.weather[0].icon == "10n")img = R.drawable.ic_cloud_drizzle
                else img = R.drawable.ic_cloud_rain
            }
            "Clouds" -> img = R.drawable.ic_cloud_cloudy
            "Clear"-> img = R.drawable.ic_cloud_lightning
        }
        cloudNormal.setImageDrawable(resources.getDrawable(img))

        adapterHour.onUpdate(dataHourWeathers)
        adapterDate.onUpdate(dataDayWeather)
    }

    override fun onStarLoad() {}

    override fun onHideLoad() {}
}
