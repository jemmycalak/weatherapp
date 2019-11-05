package com.weather.testapp.app.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

abstract class WeatherActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(onView())
        initUi(savedInstanceState)
    }

    protected abstract fun onView(): Int
    protected abstract fun initUi(savedInstanceState: Bundle?)
}
