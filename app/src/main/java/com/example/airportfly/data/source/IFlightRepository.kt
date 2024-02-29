package com.example.airportfly.data.source

import com.example.airportfly.data.Flight

interface IFlightRepository {
    suspend fun getFlights(flyType: String, airPortID: String): List<Flight>
}