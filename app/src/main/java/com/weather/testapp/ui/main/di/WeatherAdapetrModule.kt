package com.weather.testapp.ui.main.di

import android.content.Context
import com.bumptech.glide.Glide
import com.weather.testapp.BuildConfig
import com.weather.testapp.R
import com.weather.testapp.data.model.ListsWeather
import com.weather.testapp.utils.adapter.recylerviewAdapter.RecyclerviewAdapter
import com.weather.testapp.utils.adapter.recylerviewAdapter.holder.DateWeatherHolder
import com.weather.testapp.utils.adapter.recylerviewAdapter.holder.HourWeatherHolder
import com.weather.testapp.utils.converter.TimeConverter
import dagger.Module
import dagger.Provides

@Module
class WeatherAdapetrModule(val context:Context) {

    @Provides
    @WeatherScope
    fun provideHoursWeatherAdapter():RecyclerviewAdapter<ListsWeather, HourWeatherHolder>{
        val timeFormat = TimeConverter()
        val res = context.resources
        var img :Int=0
        return object: RecyclerviewAdapter<ListsWeather, HourWeatherHolder>
            (R.layout.layout_weather_hours, null, ListsWeather::class.java, HourWeatherHolder::class.java){
            override fun bindView(holder: HourWeatherHolder, model: ListsWeather, position: Int) {
                holder.temp.text= Math.round(model.main.temp).toString()+"\u00B0"
                holder.hours.text = timeFormat.HHamPM(model.dt_txt)

                when(model.weather[0].main){
                    "Rain"-> {
                        if(model.weather[0].icon == "10n")img = R.drawable.ic_cloud_drizzle
                        else img = R.drawable.ic_cloud_rain
                    }
                    "Clouds" -> img = R.drawable.ic_cloud_cloudy
                    "Clear"-> img = R.drawable.ic_cloud_lightning
                }
                holder.cloudImage.setImageDrawable(res.getDrawable(img))
            }
        }
    }

    @Provides
    @WeatherScope
    fun provideDayWeatherAdapter():RecyclerviewAdapter<ListsWeather, DateWeatherHolder>{
        val timeFormat = TimeConverter()
        val res = context.resources
        var img :Int=0
        return object : RecyclerviewAdapter<ListsWeather, DateWeatherHolder>
            (R.layout.layout_weather_date, null, ListsWeather::class.java, DateWeatherHolder::class.java){
            override fun bindView(holder: DateWeatherHolder, model: ListsWeather, position: Int) {
                holder.temp.text = Math.round(model.main.temp).toString()+"\u00B0"
                holder.day.text = timeFormat.EEEE(model.dt_txt)

                when(model.weather[0].main){
                    "Rain"-> {
                        if(model.weather[0].icon == "10n")img = R.drawable.ic_cloud_drizzle
                        else img = R.drawable.ic_cloud_rain
                    }
                    "Clouds" -> img = R.drawable.ic_cloud_cloudy
                    "Clear"-> img = R.drawable.ic_cloud_lightning
                }
                holder.imageCloud.setImageDrawable(res.getDrawable(img))
            }
        }
    }
}