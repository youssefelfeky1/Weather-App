package com.elfeky.weather_app.presentation.ui.weather_screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elfeky.weather_app.presentation.WeatherViewModel
import com.elfeky.weather_app.presentation.ui.common.AnimatedLoadingText
import com.elfeky.weather_app.presentation.ui.common.WeatherGradientBackground
import com.elfeky.weather_app.presentation.ui.weather_screen.components.CurrentWeatherSection
import com.elfeky.weather_app.presentation.ui.weather_screen.components.WeatherDetailsSection

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: WeatherViewModel
) {
    WeatherGradientBackground {
        val configuration = LocalConfiguration.current
         if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
             Column(
                 modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                 horizontalAlignment = Alignment.CenterHorizontally,
                 verticalArrangement = Arrangement.Center
             ) {
                 if (viewModel.state.isLoadingLocation) {
                     AnimatedLoadingText("Loading Location")
                 } else if (viewModel.state.isLoadingWeather) {
                     AnimatedLoadingText("Loading Weather")
                 } else if (viewModel.state.error != null) {
                     Text(viewModel.state.error!!, color = MaterialTheme.colorScheme.error)
                 } else if (viewModel.state.weatherForecast != null) {
                     CurrentWeatherSection(
                         modifier = Modifier.size(320.dp),
                         viewModel = viewModel,
                         dayForecast = viewModel.state.weatherForecast!!.days[0]
                     )
                     Spacer(Modifier.height(16.dp))
                     WeatherDetailsSection(
                         modifier = Modifier.width(320.dp),
                         dayForecast = viewModel.state.weatherForecast!!.days[0],
                         navController = navController
                     )
                 }

             }
         }else{
             Row(
                 verticalAlignment = Alignment.CenterVertically,
                 horizontalArrangement = Arrangement.Center,
                 modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())
             ) {
                 if (viewModel.state.isLoadingLocation) {
                     AnimatedLoadingText("Loading Location")
                 } else if (viewModel.state.isLoadingWeather) {
                     AnimatedLoadingText("Loading Weather")
                 } else if (viewModel.state.error != null) {
                     Text(viewModel.state.error!!, color = MaterialTheme.colorScheme.error)
                 } else if (viewModel.state.weatherForecast != null) {
                     CurrentWeatherSection(
                         modifier = Modifier.size(360.dp),
                         viewModel = viewModel,
                         dayForecast = viewModel.state.weatherForecast!!.days[0]
                     )
                     Spacer(Modifier.width(16.dp))
                     WeatherDetailsSection(
                         modifier = Modifier.size(360.dp),
                         dayForecast = viewModel.state.weatherForecast!!.days[0],
                         navController = navController
                     )
                 }
             }
         }

    }

}