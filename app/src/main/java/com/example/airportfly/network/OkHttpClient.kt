package com.example.airportfly.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

private const val API_TIME_OUT: Long = 5 * 1000

val okHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(getHttpLoggingInterceptor())
        .connectTimeout(API_TIME_OUT, TimeUnit.MILLISECONDS)
        .readTimeout(API_TIME_OUT, TimeUnit.MILLISECONDS)
        .writeTimeout(API_TIME_OUT, TimeUnit.MILLISECONDS)
        .build()

private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val log = HttpLoggingInterceptor {
        Log.v("[MY][API]", it)
    }
    log.level = HttpLoggingInterceptor.Level.BODY
    return log
}