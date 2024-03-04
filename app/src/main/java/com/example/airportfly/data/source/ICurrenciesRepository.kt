package com.example.airportfly.data.source

interface ICurrenciesRepository {
    suspend fun getRates(baseCurrency: String, currencies: String): Map<String, Double>
}