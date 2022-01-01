package com.example.weatherforecast.data.repository

import com.example.weatherforecast.data.models.network.CurrentweatherByCityNameResponse
import com.example.weatherforecast.data.models.network.OneCallWeatherResponse
import com.example.weatherforecast.data.network.WeatherApi
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api : WeatherApi) : WeatherRepository {
    override suspend fun getWeatherByLatLong(
        lat: String,
        long: String,
        units: String
    ): Response<OneCallWeatherResponse> {
        return api.getWeatherForecast(latitude = lat,longitude = long,units=units)
    }

    override suspend fun getWeatherCityName(cityName: String): Response<CurrentweatherByCityNameResponse> {
        return api.findCityWeatherData(cityName)
    }
}