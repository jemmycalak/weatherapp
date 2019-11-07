package com.weather.testapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.weather.testapp.R
import com.weather.testapp.app.base.BaseView
import com.weather.testapp.app.base.WeatherActivity
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
import com.weather.testapp.utils.network.NetworkChecker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error_network.*
import java.lang.Exception
import javax.inject.Inject

class MainActivity : WeatherActivity(), WeatherListener, BaseView, SwipeRefreshLayout.OnRefreshListener {


    @Inject
    lateinit var presenter: WeatherPresenter<WeatherListener>
    @Inject
    lateinit var adapterHour:RecyclerviewAdapter<ListsWeather, HourWeatherHolder>
    @Inject
    lateinit var adapterDate:RecyclerviewAdapter<ListsWeather, DateWeatherHolder>

    override fun onView(): Int =R.layout.activity_main

    override fun initUi(savedInstanceState: Bundle?) {
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

        refreshLayout.setOnRefreshListener(this)
        presenter.onAttach(this)
        presenter.getWeather()
    }

    override fun onUpdate(
        t: WeathersModel,
        dataHourWeathers: ArrayList<ListsWeather>,
        dataDayWeather: ArrayList<ListsWeather>,
        currentWeather: ListsWeather
    ) {
        try {
            val temp = Math.round(currentWeather.main.temp).toString()
            address.text = t.city!!.name+""
            date.text = TimeConverter().EEEEddMMMMyyyy(currentWeather.dt_txt)
            celcius.text = temp + "\u00B0"
            descCloud.text = CapitalLetter().CapsSentences(currentWeather.weather[0].description)
            var img = 0
            when (currentWeather.weather[0].main) {
                "Rain" -> {
                    if (currentWeather.weather[0].icon == "10n") img = R.drawable.ic_cloud_drizzle
                    else img = R.drawable.ic_cloud_rain
                }
                "Clouds" -> img = R.drawable.ic_cloud_cloudy
                "Clear" -> img = R.drawable.ic_cloud_lightning
            }
            cloudNormal.setImageDrawable(resources.getDrawable(img))

            adapterHour.onUpdate(dataHourWeathers)
            adapterDate.onUpdate(dataDayWeather)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun onStarLoad() {
        layoutErrorNetwork.visibility = View.GONE
        refreshLayout.isRefreshing = true
    }

    override fun onHideLoad() {
        nestedScroll.visibility = View.VISIBLE
        refreshLayout.isRefreshing = false
        layoutErrorNetwork.visibility = View.GONE
    }

    override fun onRefresh() {
        presenter.getWeather()
    }

    override fun onNetworkError() {
        nestedScroll.visibility = View.GONE
        layoutErrorNetwork.visibility = View.VISIBLE
        refreshLayout.isRefreshing = false
    }
}
