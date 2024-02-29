package com.example.airportfly.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "http://e-traffic.taichung.gov.tw/DataAPI/"
private const val API_TIME_OUT: Long = 5 * 1000

private val okHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(getHttpLoggingInterceptor())
        .connectTimeout(API_TIME_OUT, TimeUnit.MILLISECONDS)
        .readTimeout(API_TIME_OUT, TimeUnit.MILLISECONDS)
        .writeTimeout(API_TIME_OUT, TimeUnit.MILLISECONDS)
        .build()

private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object FlightApi {
    val flightService: IFlightApiService by lazy {
        retrofit.create(IFlightApiService::class.java)
    }
}

private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val log = HttpLoggingInterceptor {
        Log.v("[MY][API]", it)
    }
    log.level = HttpLoggingInterceptor.Level.BODY
    return log
}