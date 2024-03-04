package com.example.airportfly.network

import com.example.airportfly.network.response.GetRatesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ICurrencyApiService {
    /**
     * 取得匯率資訊
     * @param apikey API Key
     * @param base_currency 基準貨幣(預設 - USD)
     * @param currencies 貨幣代碼清單，以逗號分隔(預設 - 全部)
     */
    @GET("v1/latest")
    suspend fun getRates(
        @Query("apikey") apiKey: String,
        @Query("base_currency") baseCurrency: String,
        @Query("currencies", encoded = true) currencies: String
    ): GetRatesResponse
}