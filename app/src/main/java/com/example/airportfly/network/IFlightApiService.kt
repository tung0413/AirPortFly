package com.example.airportfly.network

import com.example.airportfly.data.Flight
import retrofit2.http.GET
import retrofit2.http.Path

interface IFlightApiService {
    /**
     * 取得航班資訊
     * @param flyType 資料種類 [A:入境航班, D:出境航班]
     * @param airPortID 入境/出境機場 IATA國際代碼
     *
     * http://www.ting.com.tw/agent/air-code.htm
     */
    @GET("DataAPI/api/AirPortFlyAPI/{flyType}/{airPortID}")
    suspend fun getFlights(
        @Path("flyType") flyType: String,
        @Path("airPortID") airPortID: String
    ): List<Flight>
}