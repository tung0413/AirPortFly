package com.example.airportfly.util

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtil(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isNetworkAvailable(): Boolean {
        return connectivityManager.activeNetwork != null
    }
}