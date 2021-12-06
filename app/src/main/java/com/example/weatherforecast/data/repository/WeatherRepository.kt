package com.example.weatherforecast.data.repository

import com.example.weatherforecast.data.models.network.WeatherDataResponse
import com.example.weatherforecast.data.models.network.WeeklyForecasteResponse
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeatherByCityName(city: String) : Response<WeatherDataResponse>
    suspend fun getWeatherWeekly(lat: Float,long:Float,exclude: String,units: String) : Response<WeeklyForecasteResponse>
}