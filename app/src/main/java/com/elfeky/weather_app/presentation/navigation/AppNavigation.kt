package com.elfeky.weather_app.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elfeky.weather_app.presentation.WeatherViewModel
import com.elfeky.weather_app.presentation.ui.forecast_screen.ForecastScreen
import com.elfeky.weather_app.presentation.ui.weather_screen.WeatherScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier,viewModel: WeatherViewModel) {
    val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = AppScreen.WeatherScreen.route,
            modifier = modifier.fillMaxSize()
        ) {
            composable(AppScreen.WeatherScreen.route) {
                WeatherScreen(
                    modifier = Modifier,
                    navController = navController,
                    viewModel = viewModel
                )
            }
            composable(
                route = AppScreen.ForecastScreen.route
            ) {
                ForecastScreen(modifier = Modifier, viewModel = viewModel)
            }
        }


}