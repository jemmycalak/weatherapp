package com.weather.testapp.app.di.module

import android.content.Context
import com.weather.testapp.app.di.scope.ApplicationScope
import com.weather.testapp.app.di.scope.ContextQualifier
import com.weather.testapp.utils.network.NetworkChecker
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideLogingInterceptor():HttpLoggingInterceptor{
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                Timber.i(message)
            }
        })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        return httpLoggingInterceptor
    }

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideNetworkChecker(@ContextQualifier context: Context):NetworkChecker{
        return NetworkChecker(context)
    }
}