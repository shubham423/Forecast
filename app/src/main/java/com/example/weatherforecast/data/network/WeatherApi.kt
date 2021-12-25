package com.example.weatherforecast.data.network

import com.example.weatherforecast.data.models.network.CurrentWeatherResponse
import com.example.weatherforecast.data.models.network.WeatherResponse
import com.example.weatherforecast.util.Constants.API_KEY_VALUE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun findCityWeatherData(
        @Query("q") q: String,
        @Query("appid") appid: String = API_KEY_VALUE
    ): Response<CurrentWeatherResponse>

    @GET("/data/2.5/onecall")
    suspend fun sevenDayForecast(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("units") units: String,
        @Query("appid") apiKey: String=API_KEY_VALUE
    ) : Response<WeatherResponse>


}