package com.example.airportfly.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getNowTimeString(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return LocalDateTime.now().format(formatter)
}