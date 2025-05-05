package com.elfeky.weather_app.presentation.navigation

sealed class AppScreen(val route: String) {
    object WeatherScreen : AppScreen("WeatherScreen")
    object ForecastScreen : AppScreen("ForecastScreen")
}