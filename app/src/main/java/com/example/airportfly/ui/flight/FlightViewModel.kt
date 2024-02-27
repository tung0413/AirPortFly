package com.example.airportfly.ui.flight

import androidx.lifecycle.ViewModel
import com.example.airportfly.data.source.FlightRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

private const val UPDATE_TIME: Long = 3 * 1000

class FlightViewModel(private val flightRepository: FlightRepository) : ViewModel() {
    val flights = flow {
        while (true) {
            val latestFlight = flightRepository.getFlights("D", "RMQ")
            emit(latestFlight)
            delay(UPDATE_TIME)
        }
    }
}