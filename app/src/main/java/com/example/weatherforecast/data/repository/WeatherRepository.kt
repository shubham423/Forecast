package com.example.weatherforecast.data.repository

import com.example.weatherforecast.data.models.network.WeatherDataResponse
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeatherByCityName(city: String) : Response<WeatherDataResponse>
}