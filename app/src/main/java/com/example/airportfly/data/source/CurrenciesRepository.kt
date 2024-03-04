package com.example.airportfly.data.source

import com.example.airportfly.CURRENCY_API_KEY
import com.example.airportfly.data.Currencies
import com.example.airportfly.network.CurrencyApi

class CurrenciesRepository(private val api: CurrencyApi) : ICurrenciesRepository {
    override suspend fun getRates(
        baseCurrency: String,
        currencies: String
    ): Currencies {
        return api.currencyService.getRates(CURRENCY_API_KEY, baseCurrency, currencies).data
    }
}