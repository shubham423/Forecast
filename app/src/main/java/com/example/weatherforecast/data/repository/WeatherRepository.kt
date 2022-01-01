package com.example.weatherforecast.data.repository

import com.example.weatherforecast.data.models.network.CurrentweatherByCityNameResponse
import com.example.weatherforecast.data.models.network.OneCallWeatherResponse
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeatherByLatLong(lat: String,long:String,units: String) : Response<OneCallWeatherResponse>
    suspend fun getWeatherCityName(cityName: String) : Response<CurrentweatherByCityNameResponse>
}