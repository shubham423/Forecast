package com.example.weatherforecast.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.data.models.network.CurrentweatherByCityNameResponse
import com.example.weatherforecast.data.models.network.OneCallWeatherResponse
import com.example.weatherforecast.data.repository.WeatherRepository
import com.example.weatherforecast.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _oneCallWeatherResponse = MutableLiveData<Resource<OneCallWeatherResponse>>()
    val oneCallWeatherResponse: LiveData<Resource<OneCallWeatherResponse>> = _oneCallWeatherResponse

    private val _currentWeatherResponse = MutableLiveData<Resource<CurrentweatherByCityNameResponse>>()
    val currentWeatherResponse: LiveData<Resource<CurrentweatherByCityNameResponse>> = _currentWeatherResponse

    fun getWeatherDataByLatLong(lat: String,long: String,units: String) {
        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
            Log.d("Viewmodel","call being made inside viewmodel")
            _oneCallWeatherResponse.postValue(Resource.Loading())
            viewModelScope.launch {
                val response = weatherRepository.getWeatherByLatLong(lat,long,units)
                if (response.isSuccessful) {
                    Log.d("Viewmodel","call being made inside sucesfful response")
                    _oneCallWeatherResponse.postValue(Resource.Success(response.body()!!))
                }else{
                    _oneCallWeatherResponse.postValue(Resource.Error(response.errorBody().toString()))
                    Log.d("Viewmodel","call being made with error body")
                }
            }

        }
    }

    fun getWeatherDataByCityName(cityName: String) {
        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
            Log.d("Viewmodel","call being made inside viewmodel")
            _currentWeatherResponse.postValue(Resource.Loading())
            viewModelScope.launch {
                val response = weatherRepository.getWeatherCityName(cityName)
                if (response.isSuccessful) {
                    Log.d("Viewmodel","call being made inside sucesfful response")
                    _currentWeatherResponse.postValue(Resource.Success(response.body()!!))
                }else{
                    _currentWeatherResponse.postValue(Resource.Error(response.errorBody().toString()))
                    Log.d("Viewmodel","call being made with error body")
                }
            }

        }
    }
}
