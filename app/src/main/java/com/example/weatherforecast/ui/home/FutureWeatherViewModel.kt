package com.example.weatherforecast.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FutureWeatherViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is future weather Fragment"
    }
    val text: LiveData<String> = _text
}