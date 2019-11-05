package com.weather.testapp.utils.network

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkChecker
@Inject
constructor(val context: Context) {

    fun isNetwork(): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = manager.activeNetworkInfo
        return if (info != null && info.isConnected) true else false
    }
}