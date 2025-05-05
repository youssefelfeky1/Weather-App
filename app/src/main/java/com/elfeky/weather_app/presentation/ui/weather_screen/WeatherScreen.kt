package com.elfeky.weather_app.presentation.ui.weather_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.elfeky.weather_app.presentation.navigation.AppScreen

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Weather Screen",
            modifier = Modifier.clickable {
            navController.navigate(AppScreen.ForecastScreen.route)
        }
        )
    }
}