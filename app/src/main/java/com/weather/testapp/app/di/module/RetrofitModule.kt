package com.weather.testapp.app.di.module

import com.weather.testapp.BuildConfig
import com.weather.testapp.app.di.scope.ApplicationScope
import com.weather.testapp.data.online.API
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [NetworkModule::class])
class RetrofitModule {

    @Provides
    @ApplicationScope
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BuildConfig.SERVER)
            .build()
    }
}