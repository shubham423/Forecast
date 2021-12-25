package com.example.weatherforecast.data.models.network


import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("clouds")
    val clouds: Double,
    @SerializedName("dew_poDouble")
    val dewPoDouble: Double,
    @SerializedName("dt")
    val dt: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("humidity")
    val humidity: Double,
    @SerializedName("pressure")
    val pressure: Double,
    @SerializedName("sunrise")
    val sunrise: Double,
    @SerializedName("sunset")
    val sunset: Double,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("uvi")
    val uvi: Double,
    @SerializedName("visibility")
    val visibility: Double,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind_deg")
    val windDeg: Double,
    @SerializedName("wind_gust")
    val windGust: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double
)