package com.example.airportfly.network

import com.example.airportfly.data.Flight
import retrofit2.http.GET
import retrofit2.http.Path

interface IFlightApiService {
    @GET("api/AirPortFlyAPI/{flyType}/{airPortID}")
    suspend fun getFlights(
        @Path("flyType") flyType: String,
        @Path("airPortID") airPortID: String
    ): List<Flight>
}