package com.example.weatherforecast.data.models.network


import com.google.gson.annotations.SerializedName

data class FutureWeatherResponse(
    @SerializedName("current")
    val current: Current?,
    @SerializedName("daily")
    val daily: List<Daily?>?,
    @SerializedName("hourly")
    val hourly: List<Hourly?>?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("minutely")
    val minutely: List<Minutely?>?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int?
) {
    data class Current(
        @SerializedName("clouds")
        val clouds: Int?,
        @SerializedName("dew_point")
        val dewPoint: Double?,
        @SerializedName("dt")
        val dt: Int?,
        @SerializedName("feels_like")
        val feelsLike: Double?,
        @SerializedName("humidity")
        val humidity: Int?,
        @SerializedName("pressure")
        val pressure: Int?,
        @SerializedName("sunrise")
        val sunrise: Int?,
        @SerializedName("sunset")
        val sunset: Int?,
        @SerializedName("temp")
        val temp: Double?,
        @SerializedName("uvi")
        val uvi: Double?,
        @SerializedName("visibility")
        val visibility: Int?,
        @SerializedName("weather")
        val weather: List<Weather?>?,
        @SerializedName("wind_deg")
        val windDeg: Int?,
        @SerializedName("wind_gust")
        val windGust: Double?,
        @SerializedName("wind_speed")
        val windSpeed: Double?
    )

    data class Hourly(
        @SerializedName("clouds")
        val clouds: Int?,
        @SerializedName("dew_point")
        val dewPoint: Double?,
        @SerializedName("dt")
        val dt: Int?,
        @SerializedName("feels_like")
        val feelsLike: Double?,
        @SerializedName("humidity")
        val humidity: Int?,
        @SerializedName("pop")
        val pop: Int?,
        @SerializedName("pressure")
        val pressure: Int?,
        @SerializedName("temp")
        val temp: Double?,
        @SerializedName("uvi")
        val uvi: Double?,
        @SerializedName("visibility")
        val visibility: Int?,
        @SerializedName("weather")
        val weather: List<Weather?>?,
        @SerializedName("wind_deg")
        val windDeg: Int?,
        @SerializedName("wind_gust")
        val windGust: Double?,
        @SerializedName("wind_speed")
        val windSpeed: Double?
    )

    data class Minutely(
        @SerializedName("dt")
        val dt: Int?,
        @SerializedName("precipitation")
        val precipitation: Int?
    )
}