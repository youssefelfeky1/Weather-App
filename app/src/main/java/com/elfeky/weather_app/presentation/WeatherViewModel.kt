package com.elfeky.weather_app.presentation

import androidx.lifecycle.ViewModel
import com.elfeky.weather_app.data.remote.WeatherRemoteDataSource
import com.elfeky.weather_app.data.repositories.WeatherRepositoryImpl
import com.elfeky.weather_app.common.Result


class WeatherViewModel : ViewModel() {
    private val dataSource = WeatherRemoteDataSource()
    private val repository = WeatherRepositoryImpl(dataSource)

    fun getWeatherForecast(latitude: Double, longitude: Double) {
        repository.getWeatherForecast(latitude, longitude) { result ->
            when (result) {
                is Result.Loading -> {
                }

                is Result.Success -> {

                }

                is Result.Failure -> {

                }

            }
        }
    }


}

