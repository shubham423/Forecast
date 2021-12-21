package com.example.weatherforecast.data.repository

import com.example.weatherforecast.data.models.network.CurrentWeatherResponse
import com.example.weatherforecast.data.models.network.WeatherResponse
import com.example.weatherforecast.data.network.WeatherApi
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api : WeatherApi) : WeatherRepository {
    override suspend fun getWeatherByCityName(city: String): Response<CurrentWeatherResponse> {
        return api.findCityWeatherData(city)
    }

    override suspend fun getWeatherWeekly(
        lat: Float,
        long: Float,
        units: String
    ): Response<WeatherResponse> {
        return api.sevenDayForecast(lat,long,units)
    }

}