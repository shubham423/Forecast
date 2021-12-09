package com.example.weatherforecast.data.repository

import com.example.weatherforecast.data.models.network.FutureWeatherResponse
import com.example.weatherforecast.data.models.network.WeatherDataResponse
import com.example.weatherforecast.data.network.WeatherApi
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api : WeatherApi) : WeatherRepository {
    override suspend fun getWeatherByCityName(city: String): Response<WeatherDataResponse> {
        return api.findCityWeatherData(city)
    }

    override suspend fun getWeatherWeekly(
        lat: Float,
        long: Float,
        exclude: String,
        units: String
    ): Response<FutureWeatherResponse> {
        return api.sevenDayForecast(lat,long,exclude,units)
    }

}