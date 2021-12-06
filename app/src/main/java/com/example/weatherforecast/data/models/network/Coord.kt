package com.example.weatherforecast.data.models.network


import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lat")
    val lat: Float,
    @SerializedName("lon")
    val lon: Float
)