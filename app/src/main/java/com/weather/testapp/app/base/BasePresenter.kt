package com.weather.testapp.app.base

import com.weather.testapp.app.di.scope.ApplicationScope

@ApplicationScope
interface BasePresenter<V:BaseView> {
    fun onAttach(mView:V)
}