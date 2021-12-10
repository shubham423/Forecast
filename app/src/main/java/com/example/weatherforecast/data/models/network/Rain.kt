package com.example.weatherforecast.data.models.network


import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h")
    val h: Double
)