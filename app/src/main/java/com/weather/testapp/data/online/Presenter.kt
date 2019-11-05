package com.weather.testapp.data.online

import com.weather.testapp.app.base.BasePresenter
import com.weather.testapp.app.base.BaseView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class Presenter<V : BaseView>
@Inject
constructor(): BasePresenter<V>{

    lateinit var view: V

    override fun onAttach(mView: V) {
        view = mView
    }

    protected fun <F> subcribe(observable:Observable<F>, observer:Observer<F>){
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(Schedulers.computation())
            .subscribe(observer)
    }

}