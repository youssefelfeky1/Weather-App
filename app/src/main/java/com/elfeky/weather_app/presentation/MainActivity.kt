package com.elfeky.weather_app.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.elfeky.weather_app.presentation.ui.theme.WeatherAppTheme
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import com.elfeky.weather_app.presentation.navigation.AppNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var allGranted by remember { mutableStateOf(false) }
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

                    val longitude = remember { mutableDoubleStateOf(-1000.0) }
                    val latitude = remember { mutableStateOf(-1000.0) }

                    val context = LocalContext.current

                    val locationUtils = remember { LocationUtils(context) }
                    locationUtils.getCurrentLocation(onSuccess = {
                        latitude.value = it.latitude
                        longitude.value = it.longitude
                    }, onError = {error->
                        Log.d("LOCATION", "New location: $error")
                    })
                        AppNavigation()

                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(onClick = {
                            locationLauncher.launch(
                                arrayOf(
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                )
                            )
                        }) {
                            Text("Request Location Permissions")
                        }
                    }
                }
            }
        }
    }
}