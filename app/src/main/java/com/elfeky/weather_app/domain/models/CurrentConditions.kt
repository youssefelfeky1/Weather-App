package com.elfeky.weather_app.domain.models


data class CurrentConditions(
    val datetime: String,
    val datetimeEpoch: Long,
    val temp: Double,
    val feelslike: Double,
    val humidity: Double,
    val precip: Double?,
    val windspeed: Double,
    val winddir: Double,
    val pressure: Double,
    val cloudcover: Double,
    val conditions: String,
    val icon: String,
    val sunrise: String,
    val sunset: String
)