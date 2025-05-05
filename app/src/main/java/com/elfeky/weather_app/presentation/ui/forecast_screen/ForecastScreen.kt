package com.elfeky.weather_app.presentation.ui.forecast_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.elfeky.weather_app.presentation.WeatherViewModel

@Composable
fun ForecastScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Forecast Screen")
    }
}