package com.elfeky.weather_app.domain.models


data class DayForecast(
    val datetime: String,
    val datetimeEpoch: Long,
    val temp: Double,
    val tempmin: Double,
    val tempmax: Double,
    val feelslike: Double,
    val humidity: Double,
    val precip: Double,
    val precipprob: Double,
    val windspeed: Double,
    val winddir: Double,
    val pressure: Double,
    val cloudcover: Double,
    val conditions: String,
    val icon: String,
    val sunrise: String,
    val sunset: String,
    val preciptype: List<String>?,
    val description: String,
    val uvindex: Double,
    val visibility: Double
)