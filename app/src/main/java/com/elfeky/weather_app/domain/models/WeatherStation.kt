package com.elfeky.weather_app.domain.models


data class WeatherStation(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val distance: Double,
    val quality: Int
)