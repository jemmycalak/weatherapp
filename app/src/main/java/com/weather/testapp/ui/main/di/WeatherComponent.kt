package com.weather.testapp.ui.main.di

import com.weather.testapp.app.di.component.AppComponent
import com.weather.testapp.ui.main.MainActivity
import dagger.Component

@WeatherScope
@Component(modules = [WeatherModule::class,WeatherAdapetrModule::class], dependencies = [AppComponent::class])
interface WeatherComponent {
    fun inject(activity:MainActivity)
}