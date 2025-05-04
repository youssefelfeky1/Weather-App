package com.elfeky.weather_app.domain.models

data class WeatherData(
    val queryCost: Int,
    val latitude: Double,
    val longitude: Double,
    val resolvedAddress: String,
    val timezone: String,
    val tzOffset: Double,
    val days: List<DayForecast>,
    val currentConditions: CurrentConditions?,
    val stations: Map<String, WeatherStation>
)