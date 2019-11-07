package com.weather.testapp

import com.weather.testapp.app.base.BaseView
import com.weather.testapp.data.local.sql.DataHelper
import com.weather.testapp.data.local.sql.weather.WeatherSQL
import com.weather.testapp.data.local.sql.weather.WeatherSQLPresenter
import com.weather.testapp.data.model.ListsWeather
import com.weather.testapp.data.model.WeathersModel
import com.weather.testapp.data.online.Presenter
import com.weather.testapp.data.online.presenter.weather.WeatherListener
import com.weather.testapp.ui.main.MainActivity
import com.weather.testapp.ui.main.WeatherPresenter
import com.weather.testapp.utils.network.NetworkChecker
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner
import com.weather.testapp.data.online.presenter.weather.WeatherPresenter as presenter

@RunWith(MockitoJUnitRunner::class)
class MainActivityTest {

    @Mock lateinit var weatherPresenter:WeatherPresenter<WeatherListener>
    @Mock lateinit var presenter:Presenter<BaseView>
    @Mock lateinit var sqlPresenter: WeatherSQLPresenter
    @Mock lateinit var networkChecker: NetworkChecker
    @Mock lateinit var weatherListener: WeatherListener
    @Mock lateinit var t:WeathersModel
    @Mock lateinit var dataHours:ArrayList<ListsWeather>
    @Mock lateinit var dataDay :ArrayList<ListsWeather>
    @Mock lateinit var currentWeather:ListsWeather

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        weatherListener = MainActivity()
        presenter = Presenter()
        presenter.view = weatherListener
        weatherPresenter = presenter(sqlPresenter, networkChecker)
    }

    @Test
    fun getDataWeather() {

    }
}