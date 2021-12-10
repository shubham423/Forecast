package com.example.weatherforecast.data.models.network


import com.google.gson.annotations.SerializedName

data class Minutely(
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("precipitation")
    val precipitation: Int
)