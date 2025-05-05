package com.elfeky.weather_app.presentation

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.elfeky.weather_app.data.remote.WeatherRemoteDataSource
import com.elfeky.weather_app.data.repositories.WeatherRepositoryImpl
import com.elfeky.weather_app.common.Result


class WeatherViewModel : ViewModel() {
    private val dataSource = WeatherRemoteDataSource()
    private val repository = WeatherRepositoryImpl(dataSource)

    var state by mutableStateOf(WeatherState())
        private set

    fun getWeatherForecast(latitude: Double, longitude: Double) {
        repository.getWeatherForecast(latitude, longitude) { result ->
            when (result) {
                is Result.Loading -> {
                    state = WeatherState(isLoadingLocation = false,isLoadingWeather = true)
                }

                is Result.Success -> {
                    state = WeatherState(isLoadingLocation = false,isLoadingWeather = false, weatherForecast = result.data)
                }

                is Result.Failure -> {
                    state = WeatherState(error = result.exception.message)
                }

            }
        }
    }

    fun parseIcon(context: Context, iconName: String): Int {
        return context.resources.getIdentifier(
            iconName.replace("-", "_").lowercase(),
            "drawable",
            context.packageName
        )
    }


}

