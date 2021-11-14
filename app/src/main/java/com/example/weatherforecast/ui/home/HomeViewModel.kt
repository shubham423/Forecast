package com.example.weatherforecast.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.data.models.network.WeatherDataResponse
import com.example.weatherforecast.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherResponse= MutableLiveData<Response<WeatherDataResponse>>()
    val weatherResponse : LiveData<Response<WeatherDataResponse>> = _weatherResponse

    fun getWeatherDataByCityName(city: String) {
        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
            val response = weatherRepository.getWeatherByCityName(city)
            _weatherResponse.postValue(response)
        }
    }

}