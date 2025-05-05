package com.elfeky.weather_app.presentation

import com.elfeky.weather_app.domain.models.WeatherData

data class WeatherState(
    val isLoadingLocation: Boolean = true,
    val isLoadingWeather: Boolean = false,
    val weatherForecast: WeatherData? = null,
    val error: String? = null
)
