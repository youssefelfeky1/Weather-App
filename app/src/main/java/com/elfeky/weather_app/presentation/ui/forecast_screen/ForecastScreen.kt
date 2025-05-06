package com.elfeky.weather_app.presentation.ui.forecast_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elfeky.weather_app.BuildConfig
import com.elfeky.weather_app.presentation.WeatherViewModel
import com.elfeky.weather_app.presentation.ui.common.AnimatedLoadingText
import com.elfeky.weather_app.presentation.ui.common.WeatherGradientBackground
import com.elfeky.weather_app.presentation.ui.forecast_screen.components.ForecastItem

@Composable
fun ForecastScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel,
) {
    WeatherGradientBackground {
        if (viewModel.state.isLoadingLocation) {
            AnimatedLoadingText("Loading Location")
        } else if (viewModel.state.isLoadingWeather) {
            AnimatedLoadingText("Loading Weather")
        } else if (viewModel.state.error != null) {
            Text(viewModel.state.error!!, color = MaterialTheme.colorScheme.error)
        } else if (viewModel.state.weatherForecast != null) {

            val endRange = if(BuildConfig.FLAVOR == "free") 5 else viewModel.state.weatherForecast!!.days.size-1
            LazyColumn(
                modifier = modifier.padding(top = 36.dp, bottom = 12.dp,start = 8.dp,end = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(viewModel.state.weatherForecast!!.days.slice(1..endRange)) { dayForecast ->
                    ForecastItem(
                        viewModel = viewModel,
                        dayForecast = dayForecast
                    )
                }
            }
        }
    }

}