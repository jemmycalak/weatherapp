package com.weather.testapp.utils.adapter.recylerviewAdapter.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_weather_date.view.*

class DateWeatherHolder(v:View):RecyclerView.ViewHolder(v) {
    var day:TextView
    var imageCloud:ImageView
    var temp:TextView
    init {
        day = v.date
        imageCloud = v.cloudDate
        temp = v.tempDate
    }
}