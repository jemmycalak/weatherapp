package com.weather.testapp.app.di.module

import android.content.Context
import com.weather.testapp.app.base.WeatherApp
import com.weather.testapp.app.di.scope.ApplicationScope
import com.weather.testapp.app.di.scope.ContextQualifier
import com.weather.testapp.data.local.sql.DataHelper
import com.weather.testapp.data.local.sql.weather.WeatherSQL
import com.weather.testapp.data.local.sql.weather.WeatherSQLPresenter
import dagger.Module
import dagger.Provides

@Module
class DataLocalModule(val app: WeatherApp) {

    @Provides
    @ContextQualifier
    fun provideContext(): Context {
        return app
    }

    @Provides
    @ApplicationScope
    fun provideDatalocal(@ContextQualifier context: Context): DataHelper {
        return DataHelper(context)
    }

    @Provides
    @ApplicationScope
    fun provideWeatherHelper(weatherSQL: WeatherSQL): WeatherSQLPresenter {
        return weatherSQL
    }
}