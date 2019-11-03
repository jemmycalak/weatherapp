package com.weather.testapp.utils.adapter.recylerviewAdapter.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_weather_hours.view.*

class HourWeatherHolder(v:View) : RecyclerView.ViewHolder(v) {
    var hours:TextView
    var cloudImage:ImageView
    var temp:TextView
    init {
        hours = v.hours
        cloudImage = v.cloudHours
        temp = v.celciusHours
    }
}