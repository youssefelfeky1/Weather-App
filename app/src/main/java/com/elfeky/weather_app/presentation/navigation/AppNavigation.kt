package com.elfeky.weather_app.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elfeky.weather_app.presentation.ui.forecast_screen.ForecastScreen
import com.elfeky.weather_app.presentation.ui.weather_screen.WeatherScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold {innerPadding->
        NavHost(
            navController = navController,
            startDestination = AppScreen.WeatherScreen.route,
            modifier = modifier.fillMaxSize().padding(innerPadding)
        ) {
            composable(AppScreen.WeatherScreen.route) {
                WeatherScreen(
                    modifier = Modifier,
                    navController = navController
                )
            }
            composable(
                route = AppScreen.ForecastScreen.route
            ) {
                ForecastScreen(modifier = Modifier)
            }
        }
    }

}