package com.example.airportfly.data.source

import com.example.airportfly.data.Currencies

interface ICurrenciesRepository {
    suspend fun getRates(baseCurrency: String, currencies: String): Currencies
}