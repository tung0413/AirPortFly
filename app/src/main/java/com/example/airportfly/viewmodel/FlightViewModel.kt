package com.example.airportfly.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airportfly.UPDATE_TIME
import com.example.airportfly.data.Flight
import com.example.airportfly.data.source.FlightRepository
import com.example.airportfly.network.response.ApiResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FlightViewModel(private val flightRepository: FlightRepository) : ViewModel() {
    var flightsLiveData: MutableLiveData<ApiResponse<List<Flight>>> = MutableLiveData()
        private set

    private var job: Job? = null

    fun startGetFlightsJob(flyType: String, airPortID: String) {
        flightsLiveData.value = ApiResponse.Loading
        job = viewModelScope.launch {
            while (true) {
                apiGetFlights(flyType, airPortID)
                delay(UPDATE_TIME)
            }
        }
    }

    fun cancelGetFlightsJob() {
        job?.cancel()
    }

    private fun apiGetFlights(flyType: String, airPortID: String) {
        viewModelScope.launch {
            runCatching {
                flightRepository.getFlights(flyType, airPortID)
            }.onSuccess {
                flightsLiveData.value = ApiResponse.Success(it)
            }.onFailure {
                it.printStackTrace()

                flightsLiveData.value = ApiResponse.Error(it)
                cancelGetFlightsJob()
            }
        }
    }
}