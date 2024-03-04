package com.example.airportfly.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.freecurrencyapi.com/"

private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object CurrencyApi {
    val currencyService: ICurrencyApiService by lazy {
        retrofit.create(ICurrencyApiService::class.java)
    }
}