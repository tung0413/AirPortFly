package com.example.airportfly.network.response

sealed class ApiResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : ApiResponse<T>()
    data class Error(val error: Throwable) : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
}