package com.example.airportfly.data.source

import com.example.airportfly.data.Flight
import com.example.airportfly.network.FlightApi

class FlightRepository(private val api: FlightApi) : IFlightRepository {
    override suspend fun getFlights(flyType: String, airPortID: String): List<Flight> {
        return api.flightService.getFlights(flyType, airPortID)
    }
}