package com.example.airportfly.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airportfly.UPDATE_TIME
import com.example.airportfly.data.Currencies
import com.example.airportfly.data.source.CurrenciesRepository
import com.example.airportfly.network.response.ApiResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CurrencyViewModel(private val currenciesRepository: CurrenciesRepository) : ViewModel() {

    var ratesLiveData: MutableLiveData<ApiResponse<Currencies>> = MutableLiveData()
        private set

    private var job: Job? = null

    fun startGetRatesJob() {
        ratesLiveData.value = ApiResponse.Loading
        job = viewModelScope.launch {
            while (true) {
                apiGetRates("USD", "JPY,USD,CNY,EUR,AUD,KRW")
                delay(UPDATE_TIME)
            }
        }
    }

    fun cancelGetFlightsJob() {
        job?.cancel()
    }

    private fun apiGetRates(baseCurrency: String, currencies: String) {
        viewModelScope.launch {
            runCatching {
                currenciesRepository.getRates(baseCurrency, currencies)
            }.onSuccess {
                ratesLiveData.value = ApiResponse.Success(it)
            }.onFailure {
                it.printStackTrace()

                ratesLiveData.value = ApiResponse.Error(it)
                cancelGetFlightsJob()
            }
        }
    }
}