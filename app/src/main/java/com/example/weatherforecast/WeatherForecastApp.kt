package com.example.weatherforecast

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherForecastApp : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: WeatherForecastApp? = null

        fun applicationContext(): WeatherForecastApp {
            return instance as WeatherForecastApp
        }
    }
}