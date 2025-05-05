package com.elfeky.weather_app.domain.repositories

import com.elfeky.weather_app.domain.models.WeatherData
import com.elfeky.weather_app.utils.Result

interface WeatherRepository {
    fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
        callback: (Result<WeatherData>) -> Unit
    )
}