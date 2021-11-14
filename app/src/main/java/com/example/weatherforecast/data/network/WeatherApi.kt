package com.example.weatherforecast.data.network

import com.example.weatherforecast.data.models.network.WeatherDataResponse
import com.example.weatherforecast.util.Constants.API_KEY_VALUE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    interface WeatherAppAPI {

        @GET("weather")
        suspend fun findCityWeatherData(
            @Query("q") q: String,
            @Query("appid") appid: String = API_KEY_VALUE
        ): Response<WeatherDataResponse>
    }
}