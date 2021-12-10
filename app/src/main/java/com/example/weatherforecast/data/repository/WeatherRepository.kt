package com.example.weatherforecast.data.repository

import com.example.weatherforecast.data.models.network.WeatherResponse
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeatherByCityName(city: String) : Response<WeatherResponse>
    suspend fun getWeatherWeekly(lat: Float,long:Float,units: String) : Response<WeatherResponse>
}