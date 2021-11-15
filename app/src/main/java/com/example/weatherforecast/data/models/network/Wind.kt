package com.example.weatherforecast.data.models.network


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("deg")
    val deg: Double,
    @SerializedName("speed")
    val speed: Double
)