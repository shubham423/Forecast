package com.example.weatherforecast.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.data.models.network.WeatherDataResponse
import com.example.weatherforecast.data.repository.WeatherRepository
import com.example.weatherforecast.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherResponse = MutableLiveData<Resource<WeatherDataResponse>>()
    val weatherResponse: LiveData<Resource<WeatherDataResponse>> = _weatherResponse

    fun getWeatherDataByCityName(city: String) {
        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
            Log.d("Viewmodel","call being made inside viewmodel")
            _weatherResponse.postValue(Resource.Loading())
            viewModelScope.launch {
                val response = weatherRepository.getWeatherByCityName(city)
                if (response.isSuccessful) {
                    Log.d("Viewmodel","call being made inside sucesfful response")
                    _weatherResponse.postValue(Resource.Success(response.body()!!))
                }else{
                    _weatherResponse.postValue(Resource.Error(response.errorBody().toString()))
                    Log.d("Viewmodel","call being made with error body")
                }
            }

        }
    }
}
