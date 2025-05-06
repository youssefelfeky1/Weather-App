package com.elfeky.weather_app.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.elfeky.weather_app.presentation.ui.theme.WeatherAppTheme
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.elfeky.weather_app.presentation.navigation.AppNavigation
import com.elfeky.weather_app.presentation.ui.common.WeatherGradientBackground
import com.elfeky.weather_app.utils.LocationUtils

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var allGranted by rememberSaveable { mutableStateOf(false) }
            val locationLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestMultiplePermissions(),
                onResult = { isGranted ->
                    allGranted = isGranted.all { it.value }
                }
            )
            LaunchedEffect(key1 = locationLauncher) {
                locationLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
            WeatherAppTheme {
                if (allGranted) {
                    val viewModel = remember { WeatherViewModel() }
                    val longitude = rememberSaveable { mutableDoubleStateOf(-1000.0) }
                    val latitude = rememberSaveable { mutableDoubleStateOf(-1000.0) }

                    val context = LocalContext.current

                    val locationUtils = remember { LocationUtils(context) }
                    locationUtils.getCurrentLocation(onSuccess = {
                        latitude.doubleValue = it.latitude
                        longitude.doubleValue = it.longitude
                        Log.d("LOCATION", "New location: $it.latitude, ${it.longitude}")
                        viewModel.getWeatherForecast(latitude.doubleValue, longitude.doubleValue)
                    }, onError = {error->
                        Log.d("LOCATION", "New location: $error")
                    })
                        AppNavigation(
                            viewModel = viewModel,
                            longitude = longitude,
                            latitude = latitude
                        )

                } else {
                    WeatherGradientBackground {
                        Text(
                            text = "Click me to request location permissions",
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .clickable{
                                    locationLauncher.launch(
                                        arrayOf(
                                            Manifest.permission.ACCESS_FINE_LOCATION,
                                            Manifest.permission.ACCESS_COARSE_LOCATION
                                        )
                                    )
                                }
                        )
                    }
                }
            }
        }
    }
}