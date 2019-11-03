package com.weather.testapp.app.di.module

import com.weather.testapp.app.base.BasePresenter
import com.weather.testapp.app.base.BaseView
import com.weather.testapp.app.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    @ApplicationScope
    fun providePresenter(presenter:BasePresenter<BaseView>):BasePresenter<BaseView>{
        return presenter
    }
}