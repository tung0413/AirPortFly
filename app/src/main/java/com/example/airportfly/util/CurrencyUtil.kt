package com.example.airportfly.util

import androidx.annotation.DrawableRes
import com.example.airportfly.R
import kotlin.random.Random

enum class Currency(@DrawableRes val iconRes: Int) {
    JPY(R.drawable.ic_jp),
    USD(R.drawable.ic_us),
    CNY(R.drawable.ic_cn),
    EUR(R.drawable.ic_eu),
    AUD(R.drawable.ic_au),
    KRW(R.drawable.ic_kr)
}

fun getCurrenciesForApi(): String {
    return Currency.entries.joinToString(separator = ",")
}

fun getCurrencyFromName(currencyName: String): Currency? {
    return Currency.entries.find { it.name == currencyName }
}