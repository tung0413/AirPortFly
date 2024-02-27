package com.example.airportfly.ui.flight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FlightViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is flight Fragment"
    }
    val text: LiveData<String> = _text
}